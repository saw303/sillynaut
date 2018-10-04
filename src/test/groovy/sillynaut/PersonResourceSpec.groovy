package sillynaut

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.PendingFeature
import spock.lang.Shared
import spock.lang.Specification

/**
 * @author Silvio Wangler
 */
class PersonResourceSpec extends Specification {

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    @Shared
    @AutoCleanup
    RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())

    @PendingFeature
    void "Test something"() {

        when:
        HttpRequest request = HttpRequest.GET('/')

        HttpResponse<List<Person>> response  = client.toBlocking().exchange(request)

        Optional<String> personList = response.getBody(String)

        then:
        response.status == HttpStatus.OK

        and:
        response.contentLength > 0

        and:
        personList.isPresent()
    }
}
