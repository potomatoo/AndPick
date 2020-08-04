<template>
  <v-menu
    v-model="feedContextMenu.showCtx"
    :position-x="feedContextMenu.x"
    :position-y="feedContextMenu.y"
    offset-y
    absolute
  >
    <v-list dense width="170px">
      <v-list-item>
        <!-- <v-list-item-icon> -->
        <v-icon class="mr-3">mdi-cursor-text</v-icon>
        <!-- </v-list-item-icon> -->
        <v-list-item-title>Rename</v-list-item-title>
      </v-list-item>
      <v-list-item>
        <v-icon class="mr-3">mdi-rss</v-icon>
        <v-list-item-title>Manage Sources</v-list-item-title>
      </v-list-item>
      <v-divider></v-divider>
      <v-list-item>
        <v-icon class="mr-3">mdi-trash-can-outline</v-icon>
        <v-list-item-title>Delete</v-list-item-title>
      </v-list-item>
    </v-list>
  </v-menu>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Context } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component({
  props: {
    item: Object
  }
})
export default class FeedContextMenu extends Vue {
  @feedModule.State feedContextMenu!: Context;
}
</script>

<style scoped></style>
