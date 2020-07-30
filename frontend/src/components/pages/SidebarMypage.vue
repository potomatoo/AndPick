<template>
  <div>
    <v-list>
      <v-subheader>
        Mypage
        <create-folder />
      </v-subheader>
      <v-list-item-group>
        <v-list-item v-for="postDir in postDirList" :key="postDir.postDirId">
          <v-list-item-content>
            <router-link
              :to="{ name: 'FolderMain', params: { pageName: postDir.postDirName } }"
              class="router-link"
            >
              <v-list-item-title v-text="postDir.postDirName"></v-list-item-title>
            </router-link>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
import CreateFolder from "@/components/pages/CreateFolder.vue";

const mypageModule = namespace("mypageModule");

@Component({
  components: {
    CreateFolder
  }
})
export default class SidebarMypage extends Vue {
  @mypageModule.State postDirList!: [];
  click() {
    console.log("click");
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
