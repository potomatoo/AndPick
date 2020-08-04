<template>
  <div class="container">
    <div class="row">
      <h6 class="pl-3 text-secondary font-weight-light mb-3 col-10">Mypage</h6>
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn id="optionBtn" small color="white" class="mt-3">
            <v-icon v-bind="attrs" v-on="on">mdi-menu</v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item @click="click">
            <v-list-item-title>Latest</v-list-item-title>
          </v-list-item>
          <v-list-item @click="click">
            <v-list-item-title>Oldest</v-list-item-title>
          </v-list-item>
          <v-list-item @click="updatePostDir()">
            <v-list-item-title>Rename</v-list-item-title>
          </v-list-item>
          <v-list-item @click="deletePostDir()">
            <v-list-item-title>
              <v-icon small left>mdi-delete</v-icon>Delete
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </div>

    <div class="row">
      <h1 class="pl-3 font-weight-bold">{{ postDir.postDirName }}</h1>
    </div>
    <v-divider></v-divider>
    <router-link
      :to="{ name: 'NewPost', params: { postDirId: $route.params.postDirId } }"
      class="router-link"
    >
      <v-btn small outlined color="secondary" class>
        <v-icon left>mdi-plus</v-icon>New
      </v-btn>
    </router-link>
    <div class="container">
      <div class="row">
        <draggable :list="dragList" class="row wrap sortable-list">
          <v-flex v-for="item in dragList" :key="item.length" class="sortable">
            <draggable
              :list="postDir.postList"
              class="row justify-content-start row-sm-12"
              @change="changeDrag"
            >
              <v-flex
                v-for="post in postDir.postList"
                :key="post.postId"
                xs12
                sm6
                md4
                pa-3
                class="row-v"
              >
                <v-hover v-slot:default="{ hover }" open-delay="200">
                  <router-link
                    :to="{ name: 'EditPost', params: { postDirId: $route.params.postDirId, postId: post.postId } }"
                    class="router-link"
                  >
                    <v-card
                      id="cursor_test"
                      :elevation="hover ? 16 : 2"
                      height="350"
                      max-width="350"
                    >
                      <v-card-text
                        class="font-weight-medium mt-12 text-center subtitle-1"
                      >{{ post.postTitle }}</v-card-text>
                      <hr class="mt-0" />
                      <div class="text--primary text-left ml-3">
                        <p v-html="post.postContent"></p>
                        <br />
                      </div>
                    </v-card>
                  </router-link>
                </v-hover>
              </v-flex>
            </draggable>
          </v-flex>
        </draggable>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";

import draggable from "vuedraggable";
import { namespace } from "vuex-class";

const mypageModule = namespace("mypageModule");

@Component({
  components: {
    draggable
  }
})
export default class FolderMain extends Vue {
  @mypageModule.State postDir!: [];
  @mypageModule.State postDirId!: number | null;
  @mypageModule.State postDirName!: string | null;
  @mypageModule.Mutation SELECT_POSTDIR: any;
  @mypageModule.Action FETCH_POSTDIR: any;
  @mypageModule.Action DELETE_POSTDIR: any;
  @mypageModule.Action UPDATE_POSTDIR: any;

  changeDirName = "내가 원하는 제목이 안나와요!!!!!";

  click() {
    console.log("click");
  }

  dragList = [
    {
      length: 1
    }
  ];

  deletePostDir() {
    this.DELETE_POSTDIR(this.$route.params.postDirId);
  }

  changeDrag() {
    this.FETCH_POSTDIR(this.postDir);
  }

  updatePostDir() {
    this.UPDATE_POSTDIR({
      postDirId: Number(this.$route.params.postDirId),
      postDirName: this.changeDirName
    });
  }

  @Watch("$route", { immediate: true })
  selectPostDir() {
    this.SELECT_POSTDIR({
      postDirId: this.$route.params.postDirId
    });
  }

  @Watch("postDirId", { immediate: true })
  fetchPostDir() {
    this.FETCH_POSTDIR(this.postDirId);
  }
}
</script>

<style scoped>
#cursor_test {
  cursor: pointer;
}

.router-link {
  text-decoration: none;
  color: inherit;
  border: 0;
  outline: none;
}

#editbtn {
  outline: none;
}

#optionBtn {
  outline: none;
}
</style>