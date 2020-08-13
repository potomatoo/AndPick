import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import "./styles/main.scss";

import vuetify from "./plugins/vuetify";
import Vuelidate from "vuelidate";
// import GAuth from "vue-google-oauth2";

// Vue.use(GAuth, {
//   clientId:
//     "476248660063-e2gk89ukcim2la7mbttisi10pq9ck5r6.apps.googleusercontent.com",
//   scope: "profile email https://www.googleapis.com/auth/plus.login",
// });
Vue.use(Vuelidate);
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
