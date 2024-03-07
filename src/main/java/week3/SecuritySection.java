package week3;

/**
 *  Please do not throw sausage pizza away
 *  Application layer (http, websocket)
 *  Presentation layer (ssl..)
 *  Session layer (socket)
 *  Transport layer (tcp, udp, port)
 *  Network layer (ip)
 *  Data link layer (ethernet, mac address)
 *  Physical layer (cable)
 *
 *  private network         public network                    private network
 *      User                        www.xx.com server  ->     microservices
 *
 *   device (assign mac:: - private ip)
 *      |
 *    computer(network card, mac address) -> private ip ->  NAT(network address transfer, public ip pool, route table) -> server (public ip1, 9000port)
 *                        connection = [source ip, source port, destination ip, destination port]
 *                        private ip1, 9000, public ip1, 9000
 *                        private ip2, 9000, public ip1, 9000
 *                        after NAT
 *                        public nat ip1, port1,  public ip1, 9000
 *                        public nat ip1, port2,  public ip1, 9000
 *
 *
 *      [ip header][TCP Header][Http Header][...]
 *
 *
 *
 *  Encryption vs Encoding
 *
 *  cookie , session
 *      user -> tomcat (key value pair, [session id,  session data])
 *           <- session id (SID)
 *      user ->(header: [.., cookie]) -> tomcat
 *
 *                    user
 *                     |
 *                    load balancer (ip, port,  sticky session)
 *                  /           \       \
 *      server(tomcat)1     server2     server3
 *
 *
 *
 *                  user(server ip1, ip2, ip3)
 *
 *  Authentication
 *  Authorization
 *  JWT token
 *         header.payload.signature
 *         encode(header.payload.encrypt(header.payload))
 *
 *  CORS
 *      user  ->  server(www.xx.com)  ->  server(www.yy.com)
 *
 *      server(www.xx.com) -> preflight request -> server(www.yy.com)
 *                        <- white list ,  /abc get
 *                      -> request (header: authorize origin..)
 *
 *  CSRF
 *      generate random hidden id from server
 *
 *
 *  Spring Security
 *
 *
 *
 *  SpringMVC
 *
 *      request ->  DispatcherServlet -> handler mapping -> controller
 *                          |
 *                    http message converter(@response body)
 *                         |
 *                       Jackson
 *
 *
 *      filter  <->  filter  <->  filter <->  filter <->  DispatcherServlet
 *
 *      1. username password / jwt filter
 *          dofilter() {
 *              verify
 *              save to security context (ThreadLocal)
 *          }
 *
 *
 *          @PreAuthorize('hasRole[admin, ..]')
 *     2. clean up security context
 *
 *
 *      Homework
 *          1. impl jwt filter , add spring security to rest template api homework
 *          2. read source code / learn spring security filter chain
 *          3. know how to configure spring security
 *          4. know how to configure jwt filter
 *          5. how to use @PreAuthorize
 *
 *      Friday: 10:30am cdt 开始
 *
 *
 *      Next Monday:
 *          1. sql query
 *      Next Tuesday
 *          1. transaction
 *          2. index
 *      Next Wed
 *          1. table design
 *          2. ..
 */