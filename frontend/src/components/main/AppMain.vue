<template>
  <v-main>
    <v-container fluid>
      <v-layout>
        <v-flex offset-lg3 lg6>
          <router-view></router-view>
        </v-flex>
      </v-layout>
    </v-container>
  </v-main>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

@Component
export default class AppMain extends Vue {
  mounted() {
    console.log("gkgk", this.$vuetify);
  }
}
</script>
