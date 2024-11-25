# Apache CXF Circuit Breaker

Scenario: Cascade service outage.

Solution: Fail fast, gracefully. Resume services, cautiously. Use a
circuit breaker.

# Circuit Breaker Pattern

The key concepts of the circuit breaker is that one places service
access under a management component, this component monitors the
service.

## CLOSED STATE

When all is well, the circuit breaker operates in CLOSED state - that is
to say requests are routed to their service provider.

## OPENED STATE

When requests to the service begin to fail, repeatedly the component at
some configured threshold will OPEN the circuit, halting the flow of
requests to the service (which reduces load on that service’s host) and
immediately returns an error response (no need to timeout a request
thread, reducing caller resource usage).

## HALF-OPEN STATE

When the circuit breaker is half-open, it will allow a small number of
requests to proceed to the service - it monitors for their success. If
the calls succeed, it may fully close the circuit to resume regular
operation, otherwise it will re-open the circuit.

# How do we implement this in CXF?

## JAX-WS

## JAX-RS

# Conclusion

# About the Authors

[Jamie
Goodyear](https://github.com/savoirtech/blogs/blob/main/authors/JamieGoodyear.md)

# Reaching Out

Please do not hesitate to reach out with questions and comments, here on
the Blog, or through the Savoir Technologies website at
<https://www.savoirtech.com>.

# With Thanks

Thank you to the Apache CXF community.

\(c\) 2024 Savoir Technologies
