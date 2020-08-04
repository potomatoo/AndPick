<template>
  <v-menu
    v-model="postDirContextMenu.showCtx"
    :position-x="postDirContextMenu.x"
    :position-y="postDirContextMenu.y"
    offset-y
    absolute
  >
    <v-list dense width="150px">
      <v-list-item>
        <!-- <v-list-item-icon> -->
        <v-icon class="mr-3">mdi-cursor-text</v-icon>
        <!-- </v-list-item-icon> -->
        <v-list-item-title>Rename</v-list-item-title>
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
import { Context } from "../../store/MypageInterface";

const mypageModule = namespace("mypageModule");

@Component({
  props: {
    item: Object
  }
})
export default class PostDirMenu extends Vue {
  @mypageModule.State postDirContextMenu!: Context;
}
</script>
