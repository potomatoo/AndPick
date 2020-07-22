<template>
  <div>
    <hr class="mb-0" />
    <div class="pt-3 pb-1">
      <v-list-item-title class="font-weight-bold">Board</v-list-item-title>
    </div>
    <v-list-item link to="/">
      <v-list-item-action>
        <v-icon>mdi-alpha-s-box</v-icon>
      </v-list-item-action>
      <v-list-item-content>
        <v-list-item-title>Samsung</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-list-item link to="/">
      <v-list-item-action>
        <v-icon>mdi-alpha-n-box</v-icon>
      </v-list-item-action>
      <v-list-item-content>
        <v-list-item-title>Nasa</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-list-item link to="/">
      <v-list-item-action>
        <v-icon>mdi-alpha-t-box</v-icon>
      </v-list-item-action>
      <v-list-item-content>
        <v-list-item-title>Tesla</v-list-item-title>
      </v-list-item-content>
    </v-list-item>
  </div>
</template>