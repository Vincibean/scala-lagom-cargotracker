package sample.cargotracker.shipping

import java.util.concurrent.{CompletableFuture, CompletionStage}
import javax.inject.Provider

import com.fasterxml.jackson.databind.node.{ArrayNode, JsonNodeFactory, ObjectNode}
import play.Configuration
import play.Environment
import play.api.{OptionalSourceMapper, UsefulException}
import play.api.routing.Router
import play.http.DefaultHttpErrorHandler
import play.libs.Json
import play.mvc.{Http, Result, Results}

class ErrorHandler(configuration: Configuration, environment: Environment, sourceMapper: OptionalSourceMapper, routes: Provider[Router]) extends DefaultHttpErrorHandler(configuration, environment, sourceMapper, routes) {

  override def onDevServerError(request: Http.RequestHeader, exception: UsefulException): CompletionStage[Result] = {
    val jsonError: ObjectNode = Json.newObject()
    val cause: Throwable = exception.cause
    val description: String = exception.description
    val id: String = exception.id
    val title: String = exception.title

    jsonError.put("description", description)
    jsonError.put("title", title)
    jsonError.put("id", id)
    jsonError.put("message", exception.getMessage())
    jsonError.set("cause", causesToJson(cause))

    CompletableFuture.completedFuture(Results.internalServerError(jsonError))
  }

  private def causesToJson(throwable: Throwable): ArrayNode = {
    def loop(t: Throwable, acc: ArrayNode): ArrayNode = {
      if (t != null) {
        val causeNode: ObjectNode = acc.addObject()
        causeNode.put("message", throwable.getMessage())
        causeNode.put("type", throwable.getClass().getName())
      }
      acc
    }

    loop(throwable, JsonNodeFactory.instance.arrayNode())
  }

}
