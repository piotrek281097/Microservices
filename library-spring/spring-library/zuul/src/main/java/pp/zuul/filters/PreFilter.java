package pp.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class PreFilter extends ZuulFilter {

    RestTemplate restTemplate;

    public PreFilter() {
    }

    @Autowired
    public PreFilter(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @SneakyThrows
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Map<String, String> s = ctx.getZuulRequestHeaders();
        for(String st: s.keySet())
            System.out.println(st);
        return null;
    }
}
