<template>
  <div id="app">
    <AppSidebar />
    <AppFooter />
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import AppSidebar from "@/components/AppSidebar.vue";
import AppFooter from "@/components/AppFooter.vue";

@Component({
  components: {
    AppSidebar,
    AppFooter
  }
})
export default class App extends Vue {}
</script>


