<template>
  <v-main class="row">
    <v-container class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col>
          <router-view></router-view>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

@Component
export default class AppMain extends Vue {}
</script>
