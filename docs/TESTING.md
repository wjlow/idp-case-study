# Testing Strategy

## Unit tests

`InterviewService` and `RoleExtractor` are tested with unit tests.

In `InterviewServiceTest`, I stub out the current time using `Clock` so the tests are deterministic.

Also, its dependency on `InterviewRepository` is stubbed out as opposed to mocked for the following reasons:
1. Unit tests should only care about the input and output of its dependencies
1. Mocking can lead to tautological tests. Mocking a dependency, stubbing that a method returns a result and verifying
that the method was called couples your test too closely with the code it is testing.

## Integration tests

The `InterviewController` and its Spring Security configuration `SecurityConfig` is tested using an integration test in `InterviewControllerTest`

Due to the costly nature of integration tests, we should try to avoid having too many of them. In this case,
I am only testing very basic functionality:
1. when given the `read:interviews` scope, the API returns a 200 status code with some response
1. when not given the `read:interviews` scope, the API returns a 403 status code

The integration tests bypass the actual Auth0 authentication process by providing the system under test with 
a mock user, e.g. `@WithMockUser(username = "testUser", authorities = {"SCOPE_read:interviews"})`.

## Gaps in current strategy

When working on the production version of this PoC, we need to be more thorough with our testing.

### More comprehensive API testing

1. Test when an access token is not valid (or not provided) by the client
1. Test when an access token is missing claims

We want to ensure that we provide a useful error message so the clients know what to fix.
In addition, we should push metrics and monitor them so we know how much this is impacting us.

### Testing with a real or simulated Auth0

Mocking or stubbing out Auth0 does not provide us the confidence that our system will work correctly
with Auth0.