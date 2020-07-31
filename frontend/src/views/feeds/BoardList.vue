<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex>
          <div class="caption">BOARD</div>
          <h1>{{ $route.params.boardname }}</h1>
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
        <h4>Save articles to this board</h4>
      </v-layout>
      <v-layout justify-center>
        <p>
          When you find an article in your Feed you want<br />to keep, use the
          icon to save it to this<br />board.
        </p>
      </v-layout>
    </v-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class BoardList extends Vue {}
</script>

<style></style>
