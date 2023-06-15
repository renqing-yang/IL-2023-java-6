package com.example.il2023java6.week4;


/**
 *  encode vs encrypt
 *  encoding:
 *  ------ ------ ------ ------ ------ ------ ------ ------ ------ ------ ------ ------
 *
 *      user    ->      website
 *           <- login page
 *        username password->
 *          <-  JWT (json web token)
 *
 *  JWT
 *      header.payload.signature
 *      header.
 *      payload.
 *      signature = encrypted(header.payload)
 *
 *      encode(header.payload.encrypt(header.payload))
 *
 *  HTTPS
 *      SSL
 *
 *      client                             server(private key)
 *                      -> hi
 *              <-certificate(public key)
 *          public key[generate random string/number] ->
 *              <- hash(string / number)
 *                  generate symmetric key
 *               -> symmetric key[data]
 *               <- symmetric key[data]
 *  **********
 *  Spring Security
 *                                  save userinfo into security context(Thread local)
 *                                                       |
 *                                                   verified
 *                                                      |
 *      -> UsernamePasswordAuthenticationFilter -> JWTFilter  -> authentication provider -> DaoAuthenticationProvider -> do authenticate() -> add userinfo into security context -> @PreAuthorize("hasRole['Admin']")
 *                                                                                                  |
 *                                                                                             UserDetailsService
 *                                                                                                 |
 *                                                                                             loadUsernameXX()
 *
 *  **********
 *  401 => authentication fail
 *  403 => authorization fail
 *  **********
 *  OAuth2.0
 *      open authorization
 *
 *      implicit flow
 *
 *
 *      user    ->  google login
 *       |
 *     website  <-> exchange token <-> google
 *
 *     1. visit website
 *     2. redirect frontend page to google login
 *     3. frontend gets a redirected url (authentication code? auth code ? access code?)
 *     4. send code to website
 *     5. website exchange token by using code from google server
 *
 */