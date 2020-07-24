<template>
  <div>
    <v-row>
      <v-col
        cols="12"
        sm="6"
        md="4"
        v-for="article in MyArticleList"
        :key="article.title"
        click="click"
      >
        <v-hover v-slot:default="{ hover }" open-delay="200">
          <v-card
            id="cursor_test"
            :elevation="hover ? 16 : 2"
            class="mx-auto"
            height="350"
            max-width="350"
          >
            <v-card-text class="font-weight-medium mt-12 text-center subtitle-1">{{ article.title }}</v-card-text>
          </v-card>
        </v-hover>
      </v-col>
    </v-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { mapMutations, mapState } from "vuex";

@Component({
  computed: mapState("mypageModule", ["MyArticleList"])
})
export default class FolderMain extends Vue {
  click() {
    console.log("click");
  }
}
</script>

<style scoped>
#cursor_test {
  cursor: pointer;
}
</style>