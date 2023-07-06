package hr.fer.tel.server.rest;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import hr.fer.tel.server.rest.model.ActuationForm;
import hr.fer.tel.server.rest.model.ActuationView;
import hr.fer.tel.server.rest.model.BooleanInput;
import hr.fer.tel.server.rest.model.DataExtractor;
import hr.fer.tel.server.rest.model.DateInput;
import hr.fer.tel.server.rest.model.IntegerInput;
import hr.fer.tel.server.rest.model.Key;
import hr.fer.tel.server.rest.model.Layout;
import hr.fer.tel.server.rest.model.MeasurmentSelectForm;
import hr.fer.tel.server.rest.model.MesurmentView;
import hr.fer.tel.server.rest.model.Request;
import hr.fer.tel.server.rest.model.Role;
import hr.fer.tel.server.rest.model.Scene;
import hr.fer.tel.server.rest.model.StringInput;
//import hr.fer.tel.server.rest.model.Tag;
import hr.fer.tel.server.rest.model.View;
import hr.fer.tel.server.rest.model.graph.MonitoringRelationship;
import hr.fer.tel.server.rest.model.graph.SceneGraph;
import hr.fer.tel.server.rest.model.graph.TagGraph;
import hr.fer.tel.server.rest.repository.dao.SceneRepository;
import hr.fer.tel.server.rest.repository.dao.graph.SceneGraphRepository;
import hr.fer.tel.server.rest.repository.dao.graph.TagGraphRepository;
import hr.fer.tel.server.rest.service.KeyService;
import hr.fer.tel.server.rest.service.SceneService;

@Component
@Profile("init")
public class IotFieldDataInitializer implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog( getClass() );

    @Autowired
    private SceneGraphRepository sceneGraphRepository;

    @Autowired
    private TagGraphRepository tagGraphRepository;

    @Autowired
    private KeyService keyService;

    @Autowired
    private SceneRepository sceneRepository;

    @Autowired
    private SceneService sceneService;

    @Override
    public void run(String... args) throws Exception {
        logger.info( "Starting CommandLineRunner Init DatabBase!!" );

        try {
            Key keyFerit = new Key( "influxFerit",
                    "kFNlNvr3KSAgZ0fyhY_I56bGn9HfbK6e2pu-ENx9dqltBAK38H1KySoFe27V2ri2xk3UQhO_sjP6Use0sg8q6Q==", true );
            Key keyFer = new Key( "influxFer",
                    "bzdHTbpCFmoByUgkC-l-m_8Lv2ohNadNwwPmV78ZfDMaENUcb-HKOEVLbv8QYt1hH-AWTUBwKu2gjJKlHqvGUQ==", true );

            keyService.ProbaAdd( keyFer );
            keyService.ProbaAdd( keyFerit );

            this.generateGraphInitialTags();

            this.generateAndSaveScenes();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println( "Scene generate FAILD" );
        }
    }

    private void generateAndSaveScenes() {
        var scenes = generateScenes();
        for (var scene : scenes) {
            sceneService.ProbaAddScene( scene );
        }
    }

    private void generateGraphInitialTags() {
        tagGraphRepository.deleteAll();
        sceneGraphRepository.deleteAll();

        TagGraph tagAdmin = new TagGraph( "admin" );
        TagGraph tagFer = new TagGraph( "fer" );
        TagGraph tagFerit = new TagGraph( "ferit" );
        TagGraph tagPodravka = new TagGraph( "podravka" );
        TagGraph tagFortenova = new TagGraph( "fortenova" );
        TagGraph tagAtlantic = new TagGraph( "atlantic" );
        TagGraph tagTvrtka1 = new TagGraph( "tvrtka1" );

        TagGraph tagFerVrt = new TagGraph( "fer_vrt" );
        TagGraph tagKukuruz = new TagGraph( "kukuruz" );
        TagGraph tagFao200 = new TagGraph( "fao200" );
        TagGraph tagFao400 = new TagGraph( "fao400" );
        TagGraph tagSenzori = new TagGraph( "senzori" );
        TagGraph tagTlo = new TagGraph( "tlo" );
        TagGraph tagZrak = new TagGraph( "zrak" );
        TagGraph tagTemperatura = new TagGraph( "temperatura" );

        TagGraph tagFake = new TagGraph( "fake" );


        tagAdmin.addOperates( tagFer );
        tagAdmin.addOperates( tagFerit );
        tagAdmin.addOperates( tagFake );

        tagFerit.addOperates( tagPodravka );

        tagFer.addOperates( tagFortenova );
        tagFer.addOperates( tagAtlantic );
        tagFer.addOperates( tagFerVrt );
        tagFer.addOperates( tagSenzori );


        tagSenzori.addOperates( tagTlo );
        tagSenzori.addOperates( tagZrak );
        tagSenzori.addOperates( tagTemperatura );

        tagFerVrt.addOperates( tagKukuruz );
        tagKukuruz.addOperates( tagFao200 );
        tagKukuruz.addOperates( tagFao400 );

        tagFortenova.addOperates( tagTvrtka1 );

        tagGraphRepository.saveAll(
            List.of(tagAdmin,
                tagFer,
                tagFerit,
                tagFer,
                tagFortenova,
                tagAtlantic,
                tagTvrtka1,
                tagFerVrt,
                tagKukuruz,
                tagFao200,
                tagFao400,
                tagTlo,
                tagZrak,
                tagTemperatura,
                tagSenzori,
                tagFake));
    }

    private static List<Scene> generateScenes() {
        // http://52.16.186.190/downloads/documentation/data_frame_guide.pdf
        String keyInfluxFer = "influxFer";
        String keyInfluxFerit = "influxFerit";

        Scene sceneFerTlo = new Scene( "FER-ov vrt - tlo", "mjerenja tla", new Layout( "LIST" ),
                "https://d177d01a9shl9j.cloudfront.net/online-soil/main-page-images/_859xAUTO_crop_center-center_none/what-is-topsoil.jpg",
                List.of( "tlo" ),
                List.of( sap01SoiltcView(), sap01SoilcView(),
                        sap02SoiltcView(), sap02SoilbView(), sap02SoilcView() ),
                List.of( new Role( "fer" ) ), List.of( keyInfluxFer ) );

        Scene sceneFerZrak = new Scene( "FER-ov vrt - zrak", "mjerenja zraka", new Layout( "LIST" ),
                "https://www.ccacoalition.org/sites/default/files/styles/full_content_width/public/fields/event_mainimage/cloud2.jpg?itok=XV1BpKIw&timestamp=1587998743",
                List.of( "zrak" ),
                List.of( sap01TcView(), sap01HumView(), sap01ParView(), sap01PresView(),
                        sap02TcView(), sap02HumView(), sap02PresView(),
                        sap02AneView(), sap02WvView(), sap02Plv1View(), sap02Plv2View(), sap02Plv3View() ),
                List.of( new Role( "fer" ) ), List.of( keyInfluxFer ) );

        Scene sceneFerTempTlo = new Scene( "FER-ov vrt - temp. tla", "mjerenja temperature tla", new Layout( "LIST" ),
                "https://www.gardeningknowhow.com/wp-content/uploads/2012/05/spring-temperatures-1024x768.jpg",
                List.of( "tlo", "temperatura" ),
                List.of( sap01TcView(), sap02TcView() ), List.of( new Role( "fer" ) ), List.of( keyInfluxFer ) );

        Scene sceneFerit1 = new Scene( "FERIT - 1", "sva mjerenja", new Layout( "LIST" ),
                "https://www.no-tillfarmer.com/ext/resources/images/2009/02/Corn_DT_3211396_soft.jpg",
                List.of( "ferit" ),
                List.of( ferit1airTemperature(), ferit1RelativeHumidity(), ferit1Irradiation(), ferit1atmosphericPressure(),
                        ferit1BatteryVolatage() ),
                List.of( new Role( "ferit" ) ), List.of( keyInfluxFerit ) );

        Scene sceneFerit2 = new Scene( "FERIT - 2", "sva mjerenja", new Layout( "LIST" ),
                "https://www.no-tillfarmer.com/ext/resources/images/2009/02/Corn_DT_3211396_soft.jpg",
                List.of( "ferit" ),
                List.of( ferit2temperature(), ferit2Humidity(), ferit2Illumination(),
                        ferit2lightIntensityR(), ferit2lightIntensityS(), ferit2lightIntensityT(),
                        ferit2lightIntensityU(), ferit2lightIntensityV(), ferit2lightIntensityW(),
                        ferit2WhiteIllumination(), ferit2airPressure(),
                        ferit2BateryLevel() ),
                List.of( new Role( "ferit" ) ), List.of( keyInfluxFerit ) );

        Scene gddFerit = new Scene(
                "FERIT - GDD",
                "GDD na FERIT-ovim stranicama",
                new Layout( "LIST" ),
                "https://assets-global.website-files.com/6022ede4a244183c63eed50b/6032f3f0569821be08437652_GDD.png",
                List.of( "ferit" ),
                List.of( gddFerit1() ),
                List.of( new Role( "ferit" ) ),
                List.of() );

        Scene gddFer1 = new Scene(
                "FER - GDD",
                "GDD na FER-ovim stanicama",
                new Layout( "LIST" ),
                "https://assets-global.website-files.com/6022ede4a244183c63eed50b/6032f3f0569821be08437652_GDD.png",
                List.of( "fer" ),
                List.of( gddFer1(), gddFer2() ),
                List.of( new Role( "fer" ) ),
                List.of() );


        Scene sceneFerRaniKukuruz200 = new Scene( "FER rani kukuruz", "sva mjerenja", new Layout( "LIST" ),
                "https://www.tportal.hr/media/thumbnail/900x540/61817.jpeg",
                List.of("fao200"),
                List.of( sap01TcView(), sap01HumView(), sap01ParView(), sap01SoiltcView(), sap01SoilcView(), sap01LwView(), sap01PresView(),
                        sap01BatView() ),
                List.of( new Role( "fer" ) ), List.of( keyInfluxFer, keyInfluxFerit ) );

        Scene sceneFerSrednjiKukuruz400 = new Scene( "FER srednji kukuruz", "sva mjerenja", new Layout( "LIST" ),
                "https://www.tportal.hr/media/thumbnail/900x540/61817.jpeg",
                List.of( "fao400" ),
                List.of( sap02TcView(), sap02HumView(), sap02SoiltcView(), sap02SoilbView(), sap02SoilcView(), sap02LwView(), sap02PresView(),
                        sap02AneView(), sap02Plv1View(), sap02Plv2View(), sap02Plv3View(), sap02WvView(),
                        sap02BatView() ),
                List.of( new Role( "fer" ) ), List.of( keyInfluxFer ) );

        Scene sceneFake = new Scene( "FER lažni uređaji", "mjerenja i aktuator", new Layout( "LIST" ),
            "https://upload.wikimedia.org/wikipedia/commons/2/27/Light_sensor.png",
            List.of("fake", "sensor", "actuator"),
            List.of(fakeTemperature(), fakeTemperatureConfig()),
            List.of(new Role("fer")),
            List.of(keyInfluxFer));


        return List.of(
                sceneFerRaniKukuruz200,
                sceneFerSrednjiKukuruz400,
                sceneFerTlo, sceneFerZrak,
                sceneFerTempTlo,
                sceneFerit1, sceneFerit2,
                gddFerit, gddFer1,
                sceneFake
        );
    }

    private static View fakeTemperatureConfig() {
      return new ActuationView("Fake Sensor Config", "Set configuration", "actuation",
          new ActuationForm(
              // default value request
              new Request(
                  "GET",
                  "https://iotat.tel.fer.hr:58443/fake/sensors/fakeFerSensor/airTemperature/config",
                  // headers
                  Map.of( //Map<String, String> headers,
                      "Accept", "application/json",
                      "Content-type", "application/json"
                  ),
                  // payload
                  null
              ),
              // submit request
              new Request(
                  "PUT",
                  "https://iotat.tel.fer.hr:58443/fake/sensors",
                  // headers
                  Map.of( //Map<String, String> headers,
                      "Accept", "application/json",
                      "Content-type", "application/json"
                  ),
                  // payload
                  """
                  {
                    "id": "fakeFerSensor",
                    "measurement": "airTemperature",
                    "measurementUnit": "C",
                    "period": "{{period}}",
                    "minValue": {{minValue}},
                    "maxValue": {{maxValue}},
                    "on": {{on}}
                  }
                  """
              ),
              // inputs
              List.of(
                  new StringInput("period", "Time period", 0, "Period in which sensor generates data", "PT15M", "PT[123456789]\\d*[SMH]"),
                  new IntegerInput("minValue", "Minimal value", 1, "Minimal value that can be generated", -20, -50, 50),
                  new IntegerInput("maxValue", "Maximal value", 2, "Maximal value that can be generated", 25, -50, 50),
                  new BooleanInput("on", "Generating data", 3, "Turn on/off data generation", true)
              )
          )
      );
    }

    private static View fakeTemperature() {
      return createFerView("Fake temperature", "generira slučajnu temeperaturu", "C", "airTemperature", "fakeFerSensor");
    }

    private static View gddFer1() {
        return new MesurmentView(
                "FER FAO 200 - rani",
                "datum sjetve 7.4.2022.",
                "series",
                "GDU",
                new MeasurmentSelectForm(
                        null, // submitSelectionRequest
                        List.of(
                                new DateInput( "startDate", "Početak", 1, "Datum početka grafa", "{{weekBeforeCurrentDateISO}}" ),
                                new DateInput( "endDate", "Kraj", 2, "Datum kraja grafa", "{{currentDateISO}}" )
                        )
                ),
                new Request(
                        "POST", //method
                        "https://iotat.tel.fer.hr:58443/gdd/search", //uri
                        Map.of( //Map<String, String> headers,
                                "Authorization", "bearer {{accessToken}}",
                                "Accept", "application/json",
                                "Content-type", "application/json"
                        ),
                        String.format( """
                                {
                                  "sensorId": "SAP02",
                                  "plantingDate": "2022-04-07",
                                  "startDate": "{{startDate}}",
                                  "endDate": "{{endDate}}",
                                  "minTemp": 10,
                                  "maxTemp": 30,
                                  "cumulative": true
                                }
                                """ ) //payload
                ),
                new DataExtractor( "json", "$[*].date", "$[*].value" )
        );

    }

    private static View gddFer2() {
        return new MesurmentView(
                "FER FAO 400 - srednji",
                "datum sjetve 16.4.2022.",
                "series",
                "GDU",
                new MeasurmentSelectForm(
                        null, // submitSelectionRequest
                        List.of(
                                new DateInput( "startDate", "Početak", 1, "Datum početka grafa", "{{weekBeforeCurrentDateISO}}" ),
                                new DateInput( "endDate", "Kraj", 2, "Datum kraja grafa", "{{currentDateISO}}" )
                        )
                ),
                new Request(
                        "POST", //method
                        "https://iotat.tel.fer.hr:58443/gdd/search", //uri
                        Map.of( //Map<String, String> headers,
                                "Authorization", "bearer {{accessToken}}",
                                "Accept", "application/json",
                                "Content-type", "application/json"
                        ),
                        String.format( """
                                {
                                  "sensorId": "SAP01",
                                  "plantingDate": "2022-04-16",
                                  "startDate": "{{startDate}}",
                                  "endDate": "{{endDate}}",
                                  "minTemp": 10,
                                  "maxTemp": 30,
                                  "cumulative": true
                                }
                                """ ) //payload
                ),
                new DataExtractor( "json", "$[*].date", "$[*].value" )
        );

    }


    private static View gddFerit1() {
        return new MesurmentView(
                "GDD test",
                """
                        [akcija]: GDU
                        prskanje: 200
                        žetva: 500",
                        """,
                "series",
                "GDU",
                new MeasurmentSelectForm(
                        null, // submitSelectionRequest
                        List.of(
                                new DateInput( "startDate", "Početak", 1, "Datum početka grafa", "{{weekBeforeCurrentDateISO}}" ),
                                new DateInput( "endDate", "Kraj", 2, "Datum kraja grafa", "{{currentDateISO}}" )
                        )
                ),
                new Request(
                        "POST", //method
                        "https://iotat.tel.fer.hr:58443/gdd/search", //uri
                        Map.of( //Map<String, String> headers,
                                "Authorization", "bearer {{accessToken}}",
                                "Accept", "application/json",
                                "Content-type", "application/json"
                        ),
                        String.format( """
                                {
                                  "sensorId": "0004A30B0021EF31",
                                  "plantingDate": "2022-08-01",
                                  "startDate": "{{startDate}}",
                                  "endDate": "{{endDate}}",
                                  "minTemp": 10,
                                  "maxTemp": 30,
                                  "cumulative": true
                                }
                                """ ) //payload
                ),
                new DataExtractor( "json", "$[*].date", "$[*].value" )
        );

    }


    private static View ferit2WhiteIllumination() {
        return createFeritView( "Bijela iluminacija", "", "lm*m−2", "whiteIllumination", "BE7A00000000304A" );
    }

    private static View ferit2temperature() {
        return createFeritView( "Temperatura zraka", "", "C", "temperature", "BE7A00000000304A" );
    }

    private static View ferit2lightIntensityW() {
        return createFeritView( "Intenzitet svjetla W", "", "", "lightIntensityW", "BE7A00000000304A" );
    }

    private static View ferit2lightIntensityV() {
        return createFeritView( "Intenzitet svjetla V", "", "", "lightIntensityV", "BE7A00000000304A" );
    }

    private static View ferit2lightIntensityU() {
        return createFeritView( "Intenzitet svjetla U", "", "", "lightIntensityU", "BE7A00000000304A" );
    }

    private static View ferit2lightIntensityT() {
        return createFeritView( "Intenzitet svjetla T", "", "", "lightIntensityT", "BE7A00000000304A" );
    }

    private static View ferit2lightIntensityS() {
        return createFeritView( "Intenzitet svjetla S", "", "", "lightIntensityS", "BE7A00000000304A" );
    }

    private static View ferit2lightIntensityR() {
        return createFeritView( "Intenzitet svjetla R", "", "", "lightIntensityR", "BE7A00000000304A" );
    }

    private static View ferit2Illumination() {
        return createFeritView( "Iluminacija", "", "lm*m−2", "illumination", "BE7A00000000304A" );
    }

    private static View ferit2Humidity() {
        return createFeritView( "Vlaga zraka", "", "%", "humidity", "BE7A00000000304A" );
    }

    private static View ferit2BateryLevel() {
        return createFeritView( "Baterija", "", "%", "batteryLevel", "BE7A00000000304A" );
    }

    private static View ferit2airPressure() {
        return createFeritView( "Tlak zraka", "", "Pa", "airPressure", "BE7A00000000304A" );
    }

    private static View ferit1RelativeHumidity() {
        return createFeritView( "Vlaga zraka", "", "%", "relativeHumidit", "0004A30B0021EF31" );
    }

    private static View ferit1Irradiation() {
        return createFeritView( "Solarna radijacija", "", "μmol*m-2*s-1", "irridation", "0004A30B0021EF31" );
    }

    private static View ferit1BatteryVolatage() {
        return createFeritView( "Baterijaa", "", "%", "batteryVoltage", "0004A30B0021EF31" );
    }

    private static View ferit1atmosphericPressure() {
        return createFeritView( "Vlaga zraka", "", "%", "atmosphericPressure", "0004A30B0021EF31" );
    }

    private static View ferit1airTemperature() {
        return createFeritView( "Temperatura zraka", "", "C", "airTemperature", "0004A30B0021EF31" );
    }

    private static View createFeritView(String viewTitle, String description, String measurementUnit, String measurementType,
                                        String sensorId) {
        return new MesurmentView( viewTitle, description, "series", measurementUnit, new MeasurmentSelectForm( null,
                // submitSelectionRequest
                List.of( new StringInput(
                                "aggregationWindow", "Interval", 1, "Agregacija na bazi intervala", "1d", "1d", "1h", "15m" ),
                        new DateInput( "startDateISO", "Početak", 2, "Datum početka grafa", "{{weekBeforeCurrentDateISO}}" ),
                        new DateInput( "endDateISO", "Kraj", 3, "Datum kraja grafa", "{{currentDateISO}}" ) ) ),
                new Request( "POST", // method
                        "https://iotat.tel.fer.hr:57786/api/v2/query?org=ferit", // uri
                        Map.of( // Map<String, String> headers,
                                "Authorization", "Token {{influxFerit}}", "Accept", "application/csv", "Content-type",
                                "application/vnd.flux" ),
                        String.format(
                                """
                                        from(bucket:"pio")
                                        |> range(start: {{startDateISO}}, stop: {{endTimeISO}})
                                        |> filter(fn: (r) => r._measurement == "%s" and r.device_id == "%s" and r._field == "_value")
                                        |> drop(columns: ["_start", "_stop", "_field", "host", "id"])
                                        |> window(every: {{aggregationWindow}})
                                        |> mean()
                                        |> duplicate(column: "_stop", as: "_time")
                                        |> drop(columns: ["_start", "_stop", "device_id"])
                                        """,
                                measurementType, sensorId ) // payload
                ), new DataExtractor( "csv", "_time", "_value" ) );
    }

    private static View sap02WvView() {
        return createFerView( "Smjer vjetra", "", "smjer (0 - sjever)", "WV", "SAP02" );
    }

    private static View sap02TcView() {
        return createFerView( "Temperatura zraka", "", "C", "TC", "SAP02" );
    }

    private static View sap02SoilcView() {
        return createFerView( "Vlaga tla (C)", "", "Frequency", "SOIL_C", "SAP02" );
    }

    private static View sap02SoilbView() {
        return createFerView( "Vlaga tla (B)", "", "Frequency", "SOIL_B", "SAP02" );
    }

    private static View sap02SoiltcView() {
        return createFerView( "Temperatura tla", "", "C", "SOILTC", "SAP02" );
    }

    private static View sap02PresView() {
        return createFerView( "Tlak zraka", "", "Pa", "PRES", "SAP02" );
    }

    private static View sap02Plv3View() {
        return createFerView( "Količina kiše u danu", "", "mm/day", "PLV3", "SAP02" );
    }

    private static View sap02Plv2View() {
        return createFerView( "Količina kiše u prošlom satu", "", "mm/h", "PLV2", "SAP02" );
    }

    private static View sap02Plv1View() {
        return createFerView( "Količina kiše u trenutnom satu", "", "mm", "PLV1", "SAP02" );
    }

    private static View sap02LwView() {
        return createFerView( "Vlaga lista", "", "V", "LW", "SAP02" );
    }

    private static View sap02HumView() {
        return createFerView( "Vlaga zraka", "", "%", "HUM", "SAP02" );
    }

    private static View sap02BatView() {
        return createFerView( "Baterija", "", "%", "BAT", "SAP02" );
    }

    private static View sap02AneView() {
        return createFerView( "Jačina vjetra", "", "km/h", "ANE", "SAP02" );
    }

    private static View sap01TcView() {
        return createFerView( "Temperatura zraka", "", "C", "TC", "SAP01" );
    }

    private static View sap01SoilcView() {
        return createFerView( "Vlaga tla", "Frequency", "", "SOIL_C", "SAP01" );
    }

    private static View sap01SoiltcView() {
        return createFerView( "Temperatura tla", "", "C", "SOILTC", "SAP01" );
    }

    private static View sap01PresView() {
        return createFerView( "Tlak zraka", "", "Pa", "PRES", "SAP01" );
    }

    private static View sap01ParView() {
        return createFerView( "Solarna radijacija", "", "μmol*m-2*s-1", "PAR", "SAP01" );
    }

    private static View sap01LwView() {
        return createFerView( "Vlaga lista", "", "V", "LW", "SAP01" );
    }

    private static View sap01HumView() {
        return createFerView( "Vlaga zraka", "", "%", "HUM", "SAP01" );
    }

    private static View sap01BatView() {
        return createFerView( "Baterija", "", "%", "BAT", "SAP01" );
    }

    private static View createFerView(String viewTitle, String description, String measurementUnit, String measurementType,
                                      String sensorId) {
        return new MesurmentView( viewTitle, description, "series", measurementUnit, new MeasurmentSelectForm( null,
                // submitSelectionRequest
                List.of( new StringInput(
                                "aggregationWindow", "Interval", 1, "Agregacija na bazi intervala", "1d", "1d", "1h", "15m" ),
                        new DateInput( "startDateISO", "Početak", 2, "Datum početka grafa", "{{weekBeforeCurrentDateISO}}" ),
                        new DateInput( "endDateISO", "Kraj", 3, "Datum kraja grafa", "{{currentDateISO}}" ) ) ),
                new Request( "POST", // method
                        "https://iotat.tel.fer.hr:57786/api/v2/query?org=fer", // uri
                        Map.of( // Map<String, String> headers,
                                "Authorization", "Token {{influxFer}}", "Accept", "application/csv", "Content-type",
                                "application/vnd.flux" ),
                        String.format(
                                """
                                        from(bucket:"telegraf")
                                        |> range(start: {{startDateISO}}, stop: {{endDateISO}})
                                        |> filter(fn: (r) => r._measurement == "%s" and r.id_wasp == "%s" and r._field == "value")
                                        |> drop(columns: ["_start", "_stop", "_field", "host", "id"])
                                        |> window(every: {{aggregationWindow}})
                                        |> mean()
                                        |> duplicate(column: "_stop", as: "_time")
                                        |> drop(columns: ["_start", "_stop"])
                                        """,
                                measurementType, sensorId ) // payload
                ), new DataExtractor( "csv", "_time", "_value" ) );
    }
//
//  private static View createActuationView(String title, String description, String viewType, ActuationForm form) {
//      return new ActuationView(title, description, viewType, form);
//  }
//
//  private static View createView() {
//      Random rand = new Random();
//      int number = rand.nextInt(3);
//
//      if (number == 0) {
//          View view1 = new MesurmentView("view title1", "description", "single", "C", createMeasurementForm(), createRequestQuery(),
//                  createDataExtractor());
//          return view1;
//      } else {
//          View view2 = new ActuationView("view title2", "description", "actuation", createActuationForm());
//          return view2;
//      }
//  }
//
//  private static MeasurmentSelectForm createMeasurementForm() {
//      MeasurmentSelectForm selectForm1 = new MeasurmentSelectForm(createRequestQuery(),
//          List.of(new StringInput("string", "period", "Period", "period u kojem se prikazuje graf", "\"24h\", \"7d\", \"30d\"")));
//      return selectForm1;
//  }

//  private static Request createRequestQuery() {
//      Request req = new Request("GET", "http://localhost:80/some/path/{{var1}}", createHeaders(),
//              "template {{var1}} ... {{aggregationRange, period, startTimeUTC, startTimeISO, startTimeDuration}}");
//      return req;
//  }
//
//  private static Map<String, String> createHeaders() {
//      Map<String, String> headers = new HashMap<>();
//      headers.put("{{accessToken}} {{token1}}", "application/csv");
//      return headers;
//  }
//
//  private static DataExtractor createDataExtractor() {
//      DataExtractor dataExt = new DataExtractor("csv", "_time", "_value");
//      return dataExt;
//  }
//
//  private static Map<String, String> createFormInputs() {
//      Map<String, String> input = new HashMap<>();
//      input.put("string", "integer");
//      return input;
//  }
//
//  // actuation views
//  private static View createActView() {
//      View view2 = new ActuationView("view title", "description", "actuation", createActuationForm());
//      return view2;
//  }
//
//  private static View createActView1() {
//      View view2 = new ActuationView("view title1", "description", "actuation", createActuationForm1());
//      return view2;
//  }
//
//  private static View createActView2() {
//      View view2 = new ActuationView("view title2", "description", "actuation", createActuationForm2());
//      return view2;
//  }
//
//  private static View createActView3() {
//      View view2 = new ActuationView("view title3", "description", "actuation", createActuationForm3());
//      return view2;
//  }
//
//  private static View createActView4() {
//      View view2 = new ActuationView("view title4", "description", "actuation", createActuationForm4());
//      return view2;
//  }
//
//  private static View createActView5() {
//      View view2 = new ActuationView("view title5", "description", "actuation", createActuationForm5());
//      return view2;
//  }
//
//  private static View createActView6() {
//      View view2 = new ActuationView("view title6", "description", "actuation", createActuationForm6());
//      return view2;
//  }
//
//  // actuation forme
//  private static ActuationForm createActuationForm() {
//      ActuationForm actForm1 = new ActuationForm(createRequestQuery(), createRequestQuery(),
//              List.of(new BooleanInput("biti ili ne biti", "Hamlet", "Mišolovka", true)));
//      return actForm1;
//  }
//
//  private static ActuationForm createActuationForm1() {
//      ActuationForm actForm1 = new ActuationForm(createRequestQuery(), createRequestQuery(),
//          List.of(new DateInput("datum", "rođendan", "petak", "1.1.2023.")));
//      return actForm1;
//  }
//
//  private static ActuationForm createActuationForm2() {
//      ActuationForm actForm1 = new ActuationForm(createRequestQuery(), createRequestQuery(),
//          List.of(new DecimalInput("decimal", "float", "realni broj", 0.5, 0.0, 100.0)));
//      return actForm1;
//  }
//
//  private static ActuationForm createActuationForm3() {
//      ActuationForm actForm1 = new ActuationForm(createRequestQuery(), createRequestQuery(),
//          List.of(new IntegerInput("int", "integer", "brojač", 5, 0, 1000)));
//      return actForm1;
//  }
//
//  private static ActuationForm createActuationForm4() {
//      ActuationForm actForm1 = new ActuationForm(createRequestQuery(), createRequestQuery(),
//          List.of(new StringInput("string", "str", "text", "lala", "......")));
//      return actForm1;
//  }
//
//  private static ActuationForm createActuationForm5() {
//      ActuationForm actForm1 = new ActuationForm(createRequestQuery(), createRequestQuery(),
//          List.of(new SubmitButton("samouništenje", "veliki crveni gumb")));
//      return actForm1;
//  }
//
//  private static ActuationForm createActuationForm6() {
//      ActuationForm actForm1 = new ActuationForm(createRequestQuery(), createRequestQuery(),
//          List.of(new TimeInput("vrijeme", "podne", "sredina dana", "12:00")));
//      return actForm1;
//  }

}
