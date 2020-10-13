package kz.edu.nu.cs.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@Path("/items")
public class PagingService {

    private List<String> list = new CopyOnWriteArrayList<String>();

    public PagingService() {
        for (int i = 0; i < 100; i++) {
            list.add("Eshak" + String.valueOf(i));
        }
    }

    @GET
    public Response getMyList(@QueryParam("page") int page) {
        Gson gson = new Gson();
        String json;
        PagedHelper p = new PagedHelper();

        final List<List<String>> groups = new ArrayList<>();
        for (List<Integer> indices : range(0, list.size())
                .boxed()
                .collect(groupingBy(index -> index / 10))
                .values()) {
            List<String> collect = indices
                    .stream()
                    .map(list::get)
                    .collect(toList());
            groups.add(collect);
        }

            p.setList(groups.get(page));

        if (page == 0){
            p.setPrev("disabled");
        }
        else{
            p.setPrev(String.valueOf(page-1));
        }
        if (page == 9){
            p.setNext("disabled");}
        else{
            p.setNext(String.valueOf(page+1));
        }
        json = gson.toJson(p, PagedHelper.class);
        
        return Response.ok(json).build();
    }



}
