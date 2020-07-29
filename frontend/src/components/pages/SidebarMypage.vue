<template>
  <div>
    <v-list>
      <v-subheader>
        Mypage
        <create-folder />
      </v-subheader>

      <v-list-item v-for="folder in FolderList" :key="folder.title" @click="click">
        <v-list-item-content>
          <router-link
            :to="{ name: 'FolderMain', params: { pageName: folder.title } }"
            class="router-link"
          >
            <v-list-item-title v-text="folder.title"></v-list-item-title>
          </router-link>
        </v-list-item-content>
      </v-list-item>
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
  @mypageModule.State FolderList!: [];
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
