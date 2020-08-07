<template>
  <div v-if="postItem">
    <v-menu
      v-model="postContextMenu.showCtx"
      :position-x="postContextMenu.x"
      :position-y="postContextMenu.y"
      offset-y
      absolute
    >
      <v-list dense width="150px">
        <v-list-item @click="deleteModal = true">
          <v-icon class="mr-3" color="error">mdi-trash-can-outline</v-icon>
          <v-list-item-title class="red--text">Delete</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

    <!-- 구독취소 모달 -->
    <v-dialog v-model="deleteModal" max-width="450px">
      <v-card>
        <v-card-title
          >{{ postItem.postTitle }}를 삭제하시겠습니까?</v-card-title
        >

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="deletePost">OK</v-btn>
          <v-btn text color="error" @click="deleteModal = false">CANCLE</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Context, Post } from "../../store/MypageInterface";

const mypageModule = namespace("mypageModule");

@Component({})
export default class PostContextMenu extends Vue {
  @mypageModule.State postContextMenu!: Context;
  @mypageModule.Action DELETE_POST!: any;

  @Prop({ type: Object }) readonly postItem!: Post;

  deleteModal = false;

  deletePost() {
    this.DELETE_POST({
      postId: this.postItem.postId,
      postDirId: this.postItem.postDirId
    });
    this.deleteModal = false;
  }
}
</script>
