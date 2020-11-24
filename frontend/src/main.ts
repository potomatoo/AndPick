import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import "./styles/main.scss";

import vuetify from "./plugins/vuetify";
import Vuelidate from "vuelidate";
import GAuth from "vue-google-oauth2";

Vue.use(GAuth, {
  clientId:
    `${process.env.VUE_APP_CLIENT_ID}.apps.googleusercontent.com`,
  scope: "profile email https://www.googleapis.com/auth/plus.login",
});
Vue.use(Vuelidate);
Vue.config.productionTip = false;

import VueResource from "vue-resource";
Vue.use(VueResource);

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
