### Summary
- Material Theming allows you to use Material Design in your app, with guidance on customizing colors, typography, and shapes.
- The Theme.kt file is where the theme is defined, through a composable named [your app name]+Theme()—WoofTheme() in the case of this app. Within this function, the MaterialTheme object sets the color, typography, shapes, and content of the app.
- Color.kt is where you list the colors you use in the app. Then in Theme.kt, you assign the colors in LightColorPalette and DarkColorPalette to specific slots. Not all slots need to be assigned.
- Your app can opt-in to Force Dark, which means the system will implement a dark theme for you. However, it is a better experience for your users if you implement the dark theme so that you maintain full control over the app theme.
- Shape.kt is where you define your app shapes. There are three shape sizes (small, medium, large), and you can specify how the corners are rounded.
- Shapes direct attention, identify components, communicate state, and express brand.
- Type.kt is where you initialize your fonts and assign fontFamily, fontWeight, and fontSize for the Material Design type scale.
- The Material Design type scale includes a range of contrasting styles that support the needs of your app and its content. The type scale is a combination of 15 styles that are supported by the type system.