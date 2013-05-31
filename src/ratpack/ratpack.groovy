import org.ratpackframework.groovy.templating.TemplateRenderer
import static org.ratpackframework.groovy.RatpackScript.ratpack

ratpack {
 handlers {
  get() {
    get(TemplateRenderer).render "index.html", title: "mojo"
  }

  get("test") {
    response.send "test" + " params:" + request.getQueryParams().toString();
  }

  get("test/:a") {
    def params = request.getQueryParams()
    response.send "test" + " path:" + getAllPathTokens().toString() + " params:" + params.toString();
  }

  get("test/:a/b/:b") {
    def params = request.getQueryParams()
    response.send "test/b" + " all path:" + getAllPathTokens().toString() + " path:" + getPathTokens().toString() + " params:" + params.toString();
  }
}
}
