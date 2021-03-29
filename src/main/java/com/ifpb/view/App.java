package com.ifpb.view;

import com.ifpb.model.Pessoa;
import org.neo4j.driver.*;

import java.time.LocalDate;

import static org.neo4j.driver.Values.parameters;

public class App {

    public static void main(String[] args) {

        Driver driver = GraphDatabase.driver("bolt://localhost:7687",
                AuthTokens.basic("neo4j", "neo4j1"));

//        Pessoa pessoa = new Pessoa("222.222.222-02", "Maria",
//                LocalDate.of(1993,10,25));

        try(Session session = driver.session()){

            //Adicionando uma pessoa
//            session.run("CREATE (p:Pessoa{cpf:$cpf, nome:$nome, nascimento:$nascimento})",
//                    parameters("cpf", pessoa.getCpf(), "nome", pessoa.getNome(),
//                            "nascimento", pessoa.getNascimento()));

            //Criar um relacionamento
//            session.run("MATCH (p1:Pessoa{cpf:$cpf}),(p2:Pessoa{cpf:$cpf2})" +
//                    "CREATE (p1)-[:AMIGO]->(p2)",
//                    parameters("cpf", "111.111.111-01", "cpf2", "222.222.222-02"));

            Result result = session.run("MATCH (p:Pessoa{cpf:$cpf}) RETURN p",
                    parameters("cpf", "111.111.111-01"));

            result.list().forEach(r -> System.out.println(r.get(0).asNode().values() ));

        }finally {
            driver.close();
        }

    }

}