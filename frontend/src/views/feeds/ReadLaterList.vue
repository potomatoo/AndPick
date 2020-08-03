<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex>
          <h1>Read Later</h1>
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
        <h4>NO Read Later Stories Yet</h4>
      </v-layout>
      <v-layout justify-center>
        <p>
          The articles you want to read later will be here
        </p>
      </v-layout>
    </v-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class ReadLaterList extends Vue {}
</script>

<style></style>
