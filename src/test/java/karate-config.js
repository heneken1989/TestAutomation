function fn() {
  var env = karate.env; // get system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'dev';
  }
  var config = {
    env: env,
    myVarName: 'someValue'
  }
  if (env == 'dev') {
    // customize
    // e.g. config.foo = 'bar';
  } else if (env == 'e2e') {
    // customize
  }
  config.baseUrl = "http://127.0.0.1:8000/test/checkout/"

  karate.configure('logPrettyRequest',true);
  karate.configure('logPrettyResponse',true);
  karate.configure('headers', { Accept: 'application/json' });
  
  return config;
}