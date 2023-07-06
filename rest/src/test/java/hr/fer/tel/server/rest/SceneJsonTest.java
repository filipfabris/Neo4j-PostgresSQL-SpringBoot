//package hr.fer.tel.server.rest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.net.URI;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.JsonTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.boot.test.json.JsonContent;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//
//import hr.fer.tel.server.rest.model.Layout;
//import hr.fer.tel.server.rest.model.Query;
//import hr.fer.tel.server.rest.model.Scene;
//import hr.fer.tel.server.rest.model.View;
//
//@JsonTest
//public class SceneJsonTest {
//
//    @Autowired
//    private JacksonTester<Scene> json;
//
//    @Test
//    public void testSerialize() throws Exception {
//
//        String expectedJson = "{" +
//                "\"id\":\"id scene\"," +
//                "\"title\":\"naslov\"," +
//                "\"subtitle\":\"podnaslov\"," +
//                "\"layout\":\"LIST\"," +
//                "\"tags\":[\"fer\",\"sap01\"]," +
//                "\"views\":[" +
//                "   {\"title\":\"naslov\"," +
//                    "\"query\":{" +
//                "       \"method\":\"POST\"," +
//                        "\"headers\":{" +
//                "           \"Authorization\":[\"Basic accessKey\"]}," +
//                        "\"uri\":\"/neki/uri\"}," +
//                        "\"payload\":\"template {{var1}} ... {{period, timeUTC, timeISO}}\"" +
//                    "}]" +
//                "}";
//
//        Scene scene = new Scene();
//        scene.setId("id scene");
//        scene.setTitle("naslov");
//        scene.setSubtitle("podnaslov");
//        scene.setLayout(new Layout("LIST"));
//
//        scene.setTags(List.of("fer", "sap01"));
//        Query query = new Query();
//        query.setURI(URI.create("/neki/uri"));
//        query.setMethod(HttpMethod.POST);
//
//        HttpHeaders header = new HttpHeaders();
//        header.setBasicAuth("accessKey");
//
//        query.setHeaders(header);
//
//        View view = new View("naslov", query, "template {{var1}} ... {{period, timeUTC, timeISO}}");
//        scene.setViews(List.of(view));
//
//        JsonContent<Scene> result = this.json.write(scene);
//        System.out.println(result);
//
//        assertThat(result).hasJsonPathStringValue("$.id");
//        assertThat(result).extractingJsonPathStringValue("$.id").isEqualTo("id scene");
//        assertThat(result).extractingJsonPathStringValue("$.title").isEqualTo("naslov");
//        assertThat(result).extractingJsonPathStringValue("$.subtitle").isEqualTo("podnaslov");
//        assertThat(result).isStrictlyEqualToJson(expectedJson);
//    }
//
//    @Test
//    public void testDeserialize() throws Exception{
//        String expectedJson = "{" +
//                "\"id\":\"id scene\"," +
//                "\"title\":\"naslov\"," +
//                "\"subtitle\":\"podnaslov\"," +
//                "\"layout\":\"LIST\"," +
//                "\"tags\":[\"fer\",\"sap01\"]," +
//                "\"views\":[" +
//                "   {\"title\":\"naslov\"," +
//                "\"query\":{" +
//                "       \"method\":\"POST\"," +
//                "\"headers\":{" +
//                "           \"Authorization\":[\"Basic accessKey\"]}," +
//                "\"uri\":\"/neki/uri\"}," +
//                "\"payload\":\"template {{var1}} ... {{period, timeUTC, timeISO}}\"" +
//                "}]" +
//                "}";
//
//        String e = """
//
//                """;
//
//        Scene result = this.json.parse(expectedJson).getObject();
//
//        assertThat(result.getId()).isEqualTo("id scene");
//        assertThat(result.getTitle()).isEqualTo("naslov");
//        assertThat(result.getSubtitle()).isEqualTo("podnaslov");
//        Layout l1 = new Layout("GRID");
//        Layout l2  = new Layout("LIST");
//        assertThat(result.getLayout()).isEqualTo(l2);
//        Query query = new Query();
//        query.setURI(URI.create("/neki/uri"));
//        query.setMethod(HttpMethod.POST);
//        HttpHeaders header = new HttpHeaders();
//        header.setBasicAuth("accessKey");
//        query.setHeaders(header);
//        View view = new View("naslov", query, "template {{var1}} ... {{period, timeUTC, timeISO}}");
//        assertThat(result.getViews().toString()).isEqualTo("["+view.toString()+"]");
//
//
//
//    }
//
//}
