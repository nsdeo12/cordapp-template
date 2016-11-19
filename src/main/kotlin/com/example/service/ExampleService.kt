package com.example.service

import com.example.protocol.ExampleFlow
import net.corda.core.node.PluginServiceHub

/**
 * This service registers a protocol factory we wish to use when a initiating party attempts to communicate with us
 * using a particular protocol. Registration is done against a marker class (in this case [ExampleFlow.Initiator]
 * which is sent in the session handshake by the other party. If this marker class has been registered then the
 * corresponding factory will be used to create the protocol which will communicate with the other side. If there is no
 * mapping then the session attempt is rejected.
 *
 * In short, this bit of code is required for the seller in this Example scenario to repond to the buyer using the
 * [ExampleFlow.Acceptor] protocol.
 */
object ExampleService {
    class Service(services: PluginServiceHub) {
        init {
            services.registerProtocolInitiator(ExampleFlow.Initiator::class) { ExampleFlow.Acceptor(it) }
        }
    }
}