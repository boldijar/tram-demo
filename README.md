## Hi


## Retrofit &  tikxml
I've decided to use tikxml, because SimpleXML convertor got deprecated, and Jake Wharton recommended this [here](https://github.com/square/retrofit/issues/2733)

## Koin as DI framework
Because is easy and fast

## Architecture
MVVM, using ViewModels and data binding


## Other thoughts
* I've separated the android & UI related logic to the data logic in 2 modules
* I've decided to implement the function where I see which tram I should look for in a different class so I can also create some unit tests to be sure it's working
* Using material design because it looks awesome
* Take a look at BaseActivity, is a cool class I always use in my projects (along BaseFragment, BaseDialogFragment, BaseBottomSheet) because it moves the fragment / activity logic out, and I can always switch very easily when I have to.
* Using suspend functions & coroutines & view model scope for backend requests 

Screenshots:
![Image1](ss1.png "Image 1")


![Image2](ss2.png "Image 2")

