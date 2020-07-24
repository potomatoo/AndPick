export interface SidebarItem {
  title: string;
}

export interface SidebarList {
  title: string;
  icon?: string;
  active?: boolean;
  items?: SidebarItem[];
}

export interface FeedModule {
  feedList: SidebarList[];
  boardList: SidebarList[];
}
