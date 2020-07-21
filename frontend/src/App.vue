<template>
  <v-app id="inspire">
    <v-app-bar app clipped-left>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
      <v-toolbar-title>
        <router-link :to="{ name: 'Home' }">
          <a class="navbar-brand">Junho</a>
        </router-link>
      </v-toolbar-title>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" app clipped>
      <div class="pt-3">
        <v-list-item-title class="text-center font-weight-bold">Welcome Junho!</v-list-item-title>
      </div>

      <v-list dense>
        <v-list-item link to="/register">
          <v-list-item-action>
            <v-icon>mdi-alpha-s-box</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Signup</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item link to="/profile">
          <v-list-item-action>
            <v-icon>mdi-account</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Profile</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item link to="/editor">
          <v-list-item-action>
            <v-icon>mdi-pencil</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>New Post</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item link to="/article">
          <v-list-item-action>
            <v-icon>mdi-format-list-bulleted</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Article</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item link to="/settings">
          <v-list-item-action>
            <v-icon>mdi-account-settings-variant</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Settings</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-action>
            <v-icon>mdi-alpha-l-box</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Log out</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-content class="row m-0">
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col>
            <router-view></router-view>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

@Component({})
export default class App extends Vue {
  data() {
    return {
      drawer: null
    };
  }
}
</script>


