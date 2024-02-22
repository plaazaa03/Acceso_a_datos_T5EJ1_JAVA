package org.example;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertManyResult;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

import static com.mongodb.client.model.Filters.*;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //se abre la conexion con MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");

        //crear la base de datos empresa
        MongoDatabase mongoDatabase = mongoClient.getDatabase("empresa");

        //crear una coleccion que se llame empleados
        MongoCollection<Document> documentMongoCollection = mongoDatabase.getCollection("empleados");

        //insertar datos
       /* List<Document> list = new ArrayList<>();
        list.add(new Document("_id","1").append("nombre", "Juan").append("dep", 10).append("salario",1000).append("fecha_alta", "10/10/1999"));
        list.add(new Document("_id","2").append("nombre", "Alicia").append("dep", 10).append("salario",1400).append("fecha_alta", "7/8/2000").append("oficio", "Profesora"));
        list.add(new Document("_id","3").append("nombre", "María Jesús").append("dep", 20).append("salario",1500).append("fecha_alta", "05/01/2005").append("oficio", "Analista").append("comision", 100));
        list.add(new Document("_id","4").append("nombre", "Alberto").append("dep", 20).append("salario",1100).append("fecha_alta", "15/11/2001"));
        list.add(new Document("_id","5").append("nombre", "Fernando").append("dep", 30).append("salario",1400).append("fecha_alta", "20/11/1999").append("comision", 200).append("oficio", "Analista"));
        list.add(new Document("_id","6").append("nombre", "Pedro").append("dep", 10).append("salario",1560).append("fecha_alta", "01/02/2020").append("oficio", "Analista"));
        InsertManyResult insertManyResult = documentMongoCollection.insertMany(list);
        System.out.println(insertManyResult.getInsertedIds());*/

        //visualizar empleados del departamento 10
        MongoCursor<Document> documentMongoCursor = documentMongoCollection.find(eq("dep", 10)).iterator();
        System.out.println("=======Visualizando a los empleados con departamento 10========");
        while (documentMongoCursor.hasNext()){
            Document document = documentMongoCursor.next();
            System.out.println(document.toJson());

        }
        System.out.println("");
        documentMongoCursor.close();

        //visualizar los empleados con departamento 10 o 20
        MongoCursor<Document> documentMongoCursor1 = documentMongoCollection.find(Filters.or(eq("dep", 10),eq("dep", 20))).iterator();
        System.out.println("=======Visualizando a los empleados con departamento 10 o 20========");
        while (documentMongoCursor1.hasNext()){
            Document document = documentMongoCursor1.next();
            System.out.println(document.toJson());
        }
        System.out.println("");
        documentMongoCursor1.close();

        //obtener los empleados con un salario mayor de 1300 y oficio "Profesora"
        MongoCursor<Document> documentMongoCursor2 = documentMongoCollection.find(Filters.and(gt("salario", 1300),eq("oficio", "Profesora"))).iterator();
        System.out.println("=======Obteniendo los empleados con un salario mayor de 1300 y con oficio Profesora=========");
        while (documentMongoCursor2.hasNext()){
            Document document = documentMongoCursor2.next();
            System.out.println(document.toJson());
        }
        System.out.println("");
        documentMongoCursor2.close();

        //obtener empleados con departamento 10 y oficio analista
        MongoCursor<Document> documentMongoCursor3 = documentMongoCollection.find(Filters.and(eq("dep", 10), eq("oficio", "Analista"))).iterator();
        System.out.println("=======Obteniendo empleados con departamento 10 y oficio Analista========");
        while (documentMongoCursor3.hasNext()){
            Document document = documentMongoCursor3.next();
            System.out.println(document.toJson());
        }
        System.out.println("");
        documentMongoCursor3.close();

        //obtener empleados con comision
        MongoCursor<Document> documentMongoCursor4 = documentMongoCollection.find(exists("comision")).iterator();
        System.out.println("======Obteniendo empleados con comision======");
        while (documentMongoCursor4.hasNext()){
            Document document = documentMongoCursor4.next();
            System.out.println(document.toJson());
        }
        System.out.println("");
        documentMongoCursor4.close();





    }


}
