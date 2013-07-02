import org.ratpackframework.groovy.templating.TemplateRenderer
import static org.ratpackframework.groovy.RatpackScript.ratpack

ratpack {
 handlers {
  get() {
    get(TemplateRenderer).render "index.html", title: "Template"
  }

  get("test") {
    def params = request.queryParams
    def s = params.s ?: ''
    // the simple form (or get(key)) will just get a single string
    // to get all the values for l key, use getAll(key)
    def l = params.l ? params.getAll("l").join("|") : '|';
    response.send "test" + " params:" + params.toString() + " -> s:" + s + " l:" + l
  }

  get("test/:a") {
    def params = request.queryParams
    response.send "test" + " path:" + pathTokens.toString() + " params:" + params.toString();
  }

  get("test/:a/b/:b") {
    def params = request.queryParams
    response.send "test/b" + " all path:" + getAllPathTokens().toString() + " path:" + pathTokens.toString() + " params:" + params.toString();
  }

  // show difference between allPathTokens and pathTokens
  // do a get("test/a/b/b") get("test1/a/b/b")
  prefix(":prefix") {
    get(":a") {
      response.send ":prefix/:a" + " allpath:" + allPathTokens.toString() + " path:" + pathTokens.toString()
    }
    get(":a/b/:b") {
      response.send ":prefix/:a/b/:b" + " allpath:" + allPathTokens.toString() + " path:" + pathTokens.toString()
    }
  }

  // favicon, static files
  assets "public"
 }
}
