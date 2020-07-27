<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex>
          <h1>Today</h1>
          <h3>The insights you need</h3>
        </v-flex>
        <v-flex class="text-right">
          <v-icon @click="console.log('hi')">mdi-check</v-icon>
          <v-icon class="ml-3" @click="console.log('hi')"
            >mdi-replay mdi-flip-h</v-icon
          >
        </v-flex>
      </v-layout>
    </v-container>
    <v-divider></v-divider>
    <v-container class="text-center">
      <v-layout justify-center>
        <h4>Personalize your Feed</h4>
      </v-layout>
      <v-layout justify-center>
        <p>
          The most interesting articles published by the<br />feeds you
          personally follow will be here
        </p>
      </v-layout>
      <v-btn color="success" dark>ADD ARTICLES</v-btn>
    </v-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class TodayFeedList extends Vue {}
</script>

<style></style>
