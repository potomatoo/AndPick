<template>
  <div>
    <v-divider></v-divider>
    <v-list>
      <v-subheader>Board</v-subheader>
      <v-list-group
        v-for="board in boardList"
        :key="board.title"
        v-model="board.active"
        :prepend-icon="board.action"
        no-action
      >
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title v-text="board.title"></v-list-item-title>
          </v-list-item-content>
        </template>

        <v-list-item v-for="subItem in board.items" :key="subItem.title">
          <v-list-item-content>
            <v-list-item-title v-text="subItem.title"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-group>

      <v-list-item @click="modalActive = !modalActive">
        <v-list-item-content class="text-center">
          <v-list-item-title>Create New Board</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-dialog v-model="modalActive" max-width="500px">
        <v-card>
          <v-card-text>
            <v-text-field
              v-model="newBoardName"
              label="Board Name"
              autofocus
              clearable
              :rules="rules"
              @keyup.enter="addBoards"
            ></v-text-field>

            <small class="grey--text">* Create New Board</small>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="addBoards">Create</v-btn>
            <v-btn text color="error" @click="closeModal">Cancle</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-list>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";

const feedModule = namespace("feedModule");

@Component
export default class SedebarBoard extends Vue {
  @feedModule.State boardList!: [];
  @feedModule.Mutation addBoard: any;

  newBoardName = null;

  modalActive = false;

  rules = [(value: any) => !!value || "this filed is required."];

  closeModal() {
    this.newBoardName = null;
    this.modalActive = false;
  }

  addBoards() {
    if (this.newBoardName) {
      this.addBoard({ title: this.newBoardName });
      this.newBoardName = null;
      this.closeModal();
    }
  }
}
</script>

<style scoped>
a.router-link-exact-active {
  text-decoration: none;
  color: inherit;
}
</style>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
