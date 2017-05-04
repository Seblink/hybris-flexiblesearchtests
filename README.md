# hybris-flexiblesearchtests
Hybris extension which tests the behaviour of FlexibleSearch queries.

## Run tests
To run the tests, the extension should be checked out and added to an Hybris platform. To do this, check out the extension in the `custom` extensions directory of the intallation, and add the following line to the `localextensions.xml` file:
```
<extension name="flexiblesearchtests"/>
```

Next, execute the following commandos in the platform directory:
```
ant clean all
ant yunitupdate
ant alltests -Dtestclasses.extensions=flexiblesearchtests
```

## References
See [the blog post about the Hybris FlexibleSearch API](https://seblink.wordpress.com/2017/05/04/the-hybris-flexiblesearch-api/) for more information.
