<template>
  <div id="app">
    <AppSidebar />
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import AppSidebar from "@/components/main/AppSidebar.vue";

@Component({
  components: {
    AppSidebar
  }
})
export default class App extends Vue {}
</script>


