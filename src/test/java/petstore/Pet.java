package petstore;


import org.testng.annotations.Test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

// Classe
public class Pet  {
    //atributos
    String uri = "https://petstore.swagger.io/v2/pet";

    //métodos e funções
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson))) ;

    }

    //incluir -create - post
    @Test  //identifica o método ou função como um teste para TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        //sintaxe gherkin
        //dado - quando - então
        //given- when - then

        given()//dado
                .contentType("application/json")//comum em api rest - os mais antigos text/xml
                .log().all()
                .body(jsonBody)
        .when()//quando
                .post(uri)
        .then()//então
                .log().all()
                .statusCode(200)
                .body("name", is("snoopy"))
                .body("status", is("available"))
                //.body("category.id", contains("1"))
                //.body("category.id", is("1"))
                .body("category.name", is("dog"))
                .body("tags.name", contains("sta"))


        ;
    }
}
