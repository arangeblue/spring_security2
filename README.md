# spring security study

- 스프링의 시큐리티 라이브러리를 사용하면 인증 권한과 관련한 많은 서비스를 제공받을 수 있다.
- 실무에서는 이를 그대로 사용할 수 없으며 custom을 하여 더 세부적인 설정을 한 후 사용할 수 있다.
- 이 때 WebSecurityConfigurerAdapter를 상속받아서 나만의 custom security config 를 만들면 된다.
- 기본적으로 user라는 name을 가지고 spring security에서 제공하는 랜덤 password를 가지고 로그인을 하면 된다.
- 더 나아가 db에 사용자와 관리자를 등록해서 이들을 인증하는 것까지 해볼 것..
  


- **password를 해줄 때 encode를 해주어야 한다.**


- roles에 따른 권한을 만들어주어야 한다.
- @PreAuthorize()를 이용해서 antMatchers처럼 행동할 수 있다.

## permission




