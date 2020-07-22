<template>
  <div>
    <hr class="mb-0" />
    <div class="pt-3 pb-1">
      <v-list-item-title class="font-weight-bold">Feeds</v-list-item-title>
    </div>

    <v-list-item link to="/">
      <v-list-item-action>
        <v-icon>mdi-alpha-n-box</v-icon>
      </v-list-item-action>
      <v-list-item-content>
        <v-list-item-title>Naver</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-list-item link to="/">
      <v-list-item-action>
        <v-icon>mdi-alpha-g-box</v-icon>
      </v-list-item-action>
      <v-list-item-content>
        <v-list-item-title>Google</v-list-item-title>
      </v-list-item-content>
    </v-list-item>
  </div>
</template>