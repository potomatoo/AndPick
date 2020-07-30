<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex>
          <h1>{{ $route.params.feedName }}</h1>
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
        <h4>Which sources would you like to follow?</h4>
      </v-layout>
      <v-layout justify-center>
        <p>
          You can follow publications, blogs feeds.
        </p>
      </v-layout>
      <v-btn color="success" dark>ADD CONTENT</v-btn>
    </v-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class FeedPage extends Vue {}
</script>

<style></style>
