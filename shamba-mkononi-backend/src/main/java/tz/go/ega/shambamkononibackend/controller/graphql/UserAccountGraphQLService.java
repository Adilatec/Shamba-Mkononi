package tz.go.ega.shambamkononibackend.controller.graphql;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Service;
import tz.go.ega.shambamkononibackend.payload.response.Response;
import tz.go.ega.shambamkononibackend.util.ResponseCode;

@Service
@GraphQLApi
public class UserAccountGraphQLService {

    @GraphQLQuery(name = "getHelloWorld")
    public Response<String> getHelloWorld(){
        return new Response<>(false, ResponseCode.SUCCESS, "Hello World!");
    }

}
