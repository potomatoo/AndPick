<template>
  <v-container fluid>
    <v-row>
      <v-col cols="12" md="6">
        <v-textarea
          v-model="EditArticle"
          outlined
          auto-grow
          row-height="50"
          name="beforeEdit"
          label="Before Edit"
        ></v-textarea>
      </v-col>

      <v-col cols="12" md="6">
        <v-textarea
          v-model="EditArticle"
          outlined
          auto-grow
          row-height="50"
          name="afterEdit"
          label="After Edit"
        ></v-textarea>
      </v-col>
    </v-row>
    <div class="text-right">
      <v-btn text large color="#5cb85c" style="outline: none">Save</v-btn>
    </div>
  </v-container>
</template>
<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component({})
export default class EditArticle extends Vue {
  EditArticle = "";
}
</script>

<style scoped>
</style>