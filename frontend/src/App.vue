<template>
  <div id="app">
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
