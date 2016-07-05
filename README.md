# CleanCode
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-NavigationTabBar-blue.svg?style=flat-square)](http://android-arsenal.com/details/1/3382)
[![Download](https://api.bintray.com/packages/gigamole/maven/navigationtabbar/images/download.svg) ](https://bintray.com/gigamole/maven/navigationtabbar/_latestVersion)

BottomTabs
===================

Bottom Navigation tab with configurable activities. 

Horizontal NTB | NTB with bottom behavior | NTB with selected icons | NTB with CoordinatorLayout | Vertical NTB | NTB Samples |
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
![](https://lh6.googleusercontent.com/-Bf7uxUiRvfk/VvpVlkZzsVI/AAAAAAAACPA/Ysg9uuBpaL8UhsXpYPlyNJK6IJssdkMvg/w325-h552-no/hntb.gif)|![](https://lh4.googleusercontent.com/-mF70XCnMpgk/V1NnK34tnhI/AAAAAAAACkY/z0Z15Q_7gg4fMovWiEvo9agJgz7m933cQCL0B/w323-h552-no/btbntb.gif)|![](https://lh5.googleusercontent.com/-LcHHajuKNzw/Vz77El2lHsI/AAAAAAAACiQ/I0CjrMUP6R4ioH9h8nEe37LCqXmb3GJKACL0B/w317-h552-no/ntbsi.gif)|![](https://lh6.googleusercontent.com/-hMvLn-jzY3k/VzcPrGAmr4I/AAAAAAAACc0/US0yokfG23kQJEAPxFoPp-8lOUNRSPV9QCL0B/w321-h552-no/cltntb.gif)|![](https://lh4.googleusercontent.com/-k4Ac7-c2m8E/VvpVlk3ZmLI/AAAAAAAACPA/21ISoAYGZzUlvGPmIauXwfYZOKdCYIRGg/w323-h552-no/vntb.gif)|![](https://lh5.googleusercontent.com/-hmELfZQvexU/VvpVlooaPvI/AAAAAAAACPA/5HA5ic7dASwBUYqpqcfxAmfLzPPDXejqQ/w322-h552-no/ntbs.gif)
U can check the sample app [here](https://github.com/DevLight-Mobile-Agency/NavigationTabBar/tree/master/app).

Download
------------

You can download a .aar` from GitHub's [releases page](https://github.com/DevLight-Mobile-Agency/NavigationTabBar/releases).

Or use Gradle jCenter:

```groovy
dependencies {
    repositories {
        mavenCentral()
        maven {
            url  'http://dl.bintray.com/gigamole/maven/'
        }
    }
    compile 'com.github.devlight.navigationtabbar:navigationtabbar:+'
}
```

Or Gradle Maven Central:

```groovy
compile 'com.github.devlight.navigationtabbar:navigationtabbar:1.2.2'
```

Or Maven:

```groovy
<dependency>
    <groupId>com.github.devlight.navigationtabbar</groupId>
    <artifactId>navigationtabbar</artifactId>
    <version>1.2.2</version>
    <type>aar</type>
</dependency>
```

Android SDK Version
=========

NavigationTabBar requires a minimum SDK version of 11.

Sample
========

<b>Parameters</b>

For NTB you can set such parameters as:

 - models:

     allows you to set NTB models, where you set icon and color. Can be set up only via code.

 - behavior:

     allows you to set bottom translation behavior.

 - view pager:

     allows you to connect NTB with ViewPager. If you want your can also set OnPageChangeListener.

 - background:

    allows you to set background to NTB which automatically set with offset relative to badge gravity.

 - model selected icon:

     allows you to set selected icon when current model is active.

 - model title:

     allows you to enable title in you model.

 - model badge:

     allows you to enable badge in you model.

 - use custom typeface on badge:

     allows you to handle set of custom typeface in your badge.

 - title mode:

     allows you to handle mode of the model title show. Can show all or only active.

 - scale mode:

     allows you to handle mode of the model icon and title scale.

 - tint mode:

      allows you to enable or disable icon tinting.

 - badge position:

     allows you to set the badge position in you model. Can be: left(25%), center(50%) and right(75%).

 - badge gravity:

     allows you to set the badge gravity in NTB. Can be top or bottom.

 - badge colors:

      allows you to set the badge bg and title colors.

 - typeface:

     allows you to set custom typeface to your title.

 - corners radius:

     allows you to set corners radius of pointer.

 - animation duration:

     allows you to set animation duration.

 - inactive color:

     allows you to set inactive icon color.

 - active color:

     allows you to set active icon color.

 - tab bar listener:

     allows you to set listener which triggering on start or on end when you set model index.

 - preview colors:

     allows you to set preview colors, which generate count of models equals to count of colors.

<b>Tips</b>

Creation of models occurs through Builder pattern.

ModelBuilder requires two fields: icon and color. Title, badge title and selected icon is optional.

You can set selected icon. Resize and scale of selected icon equals to original icon.

Orientation automatically detected according to view size.

By default badge bg color is the active model color and badge title color is the model bg color. To reset colors just set badge bg and title color to 0.

If your set ViewPager you can action down on active pointer and do like drag.

If you want to set the background to NTB, just set background via XML or code and its automatically set relative to badge gravity.


<b>Init</b>

Check out in code init:

```java
final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
models.add(
        new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_first),
                Color.parseColor(colors[0])
        ).title("Heart")
                .badgeTitle("NTB")
                .build()
);
models.add(
        new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_second),
                Color.parseColor(colors[1])
        ).title("Cup")
                .badgeTitle("with")
                .build()
);
models.add(
        new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_third),
                Color.parseColor(colors[2])
        ).title("Diploma")
                .badgeTitle("state")
                .build()
);
models.add(
        new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_fourth),
                Color.parseColor(colors[3])
        ).title("Flag")
                .badgeTitle("icon")
                .build()
);
models.add(
        new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_fifth),
                Color.parseColor(colors[4])
        ).title("Medal")
                .badgeTitle("777")
                .build()
);
navigationTabBar.setModels(models);
navigationTabBar.setViewPager(viewPager, 2);

navigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
navigationTabBar.setBadgeGravity(NavigationTabBar.BadgeGravity.BOTTOM);
navigationTabBar.setBadgePosition(NavigationTabBar.BadgePosition.CENTER);
navigationTabBar.setTypeface("fonts/custom_font.ttf");
navigationTabBar.setIsBadged(true);
navigationTabBar.setIsTitled(true);
navigationTabBar.setIsTinted(true);
navigationTabBar.setIsBadgeUseTypeface(true);
navigationTabBar.setBadgeBgColor(Color.RED);
navigationTabBar.setBadgeTitleColor(Color.WHITE);
```

If your models is in badge mode you can set title, hide, show, toggle and update badge title like this:
```java
model.setTitle("Here some title to model");
model.hideBadge();
model.showBadge();
model.toggleBadge();
model.updateBadgeTitle("Here some title like NEW or some integer value");
```

To enable behavior translation inside CoordinatorLayout when at bottom of screen:
```java
bottomNavigation.setBehaviorEnabled(true);
```

Other methods check out in sample.

And XML init:

```xml
<com.gigamole.navigationtabbar.ntb.NavigationTabBar
   android:id="@+id/ntb"
   android:layout_width="match_parent"
   android:layout_height="50dp"
   app:ntb_animation_duration="400"
   app:ntb_preview_colors="@array/colors"
   app:ntb_corners_radius="10dp"
   app:ntb_active_color="#fff"
   app:ntb_inactive_color="#000"
   app:ntb_badged="true"
   app:ntb_titled="true"
   app:ntb_scaled="true"
   app:ntb_tinted="true"
   app:ntb_title_mode="all"
   app:ntb_badge_position="right"
   app:ntb_badge_gravity="top"
   app:ntb_badge_bg_color="#ffff0000"
   app:ntb_badge_title_color="#ffffffff"
   app:ntb_typeface="fonts/custom_typeface.ttf"
   app:ntb_badge_use_typeface="true"/>
```

Getting Help
======

To report a specific problem or feature request, [open a new issue on Github](https://github.com/DevLight-Mobile-Agency/NavigationTabBar/issues/new).

License
======

Apache 2.0 and MIT. See [LICENSE](https://github.com/DevLight-Mobile-Agency/NavigationTabBar/blob/master/LICENSE.txt) file for details.

Inspiration
======

TapBar interactions| Circle interactions | Title interactions
:-------------------------:|:-------------------------:|:-------------------------:
![](https://s-media-cache-ak0.pinimg.com/originals/39/ee/33/39ee330f3460bd638284f0576bc95b65.gif)|![](https://s-media-cache-ak0.pinimg.com/564x/f4/0d/a9/f40da9e5b73eb5e0e46681eba38f1347.jpg)|![](https://s-media-cache-ak0.pinimg.com/564x/14/eb/dd/14ebddfc0d92f02be3d61ede48a9da6e.jpg)

Thanks to [Valery Nuzhniy](https://www.pinterest.com/hevbolt/) for NTB badge design.

Author
=======

Made in [Yang Feng](https://github.com/AndroidClasses)

Created by [Basil Miller](https://github.com/GIGAMOLE) - [@gigamole](mailto:e13310@gmail.com)
