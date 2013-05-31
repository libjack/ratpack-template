import org.ratpackframework.groovy.templating.TemplateRenderer
import static org.ratpackframework.groovy.RatpackScript.ratpack

ratpack {
 handlers {
  get() {
    get(TemplateRenderer).render "index.html", title: "mojo"
  }

  get("test") {
    def params = request.getQueryParams()
    def b = params.b
    def s = params.s ?: ''
    def l = params.l ? params.getAll("l").join("|") : '|';
    // if just get, the delagate will return a single string, 
    //def l = params.get("l") ?: ''
    response.send "test" + " params:" + params.toString() + " b:" + (b?'T':'F') + " s:" + s + " l:" + l
  }

  get("test/:a") {
    def params = request.getQueryParams()
    response.send "test" + " path:" + getAllPathTokens().toString() + " params:" + params.toString();
  }

  get("test/:a/b/:b") {
    def params = request.getQueryParams()
    response.send "test/b" + " all path:" + getAllPathTokens().toString() + " path:" + getPathTokens().toString() + " params:" + params.toString();
  }

  assets "public"
 }
}
