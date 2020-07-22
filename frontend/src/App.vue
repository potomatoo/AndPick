<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link :to="{ name: 'Signup' }">Signup</router-link> |
      <router-link :to="{ name: 'Login' }">Login</router-link> |
    </div>
    <router-view />
    <Home />
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import Home from "@/views/Home.vue";

@Component({
  components: {
    Home,
  },
})
export default class App extends Vue {}
</script>
