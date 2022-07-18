<header class="header">
    <div class="navigation-trigger" data-ma-action="aside-open" data-ma-target=".sidebar">
        <div class="navigation-trigger__inner">
            <i class="navigation-trigger__line"></i> <i class="navigation-trigger__line"></i> <i class="navigation-trigger__line"></i>
        </div>
    </div>
    <div class="header__logo hidden-sm-down">
        <h1 class="text-white">Welcome - ${userRole}</h1>
    </div>
    <ul class="top-nav">
        <li class="dropdown hidden-xs-down"><a href="" data-toggle="dropdown"><i class="zmdi zmdi-apps"></i></a>
            <div class="dropdown-menu dropdown-menu-right dropdown-menu--block" role="menu">
                <div class="row app-shortcuts">
                    <a class="col-4 app-shortcuts__item" href=""> <i
						class="zmdi zmdi-calendar"></i> <small class="">Calendar</small> <span
						class="app-shortcuts__helper bg-red"></span>
					</a> <a class="col-4 app-shortcuts__item" href=""> <i
						class="zmdi zmdi-file-text"></i> <small class="">Files</small> <span
						class="app-shortcuts__helper bg-blue"></span>
					</a> <a class="col-4 app-shortcuts__item" href=""> <i
						class="zmdi zmdi-email"></i> <small class="">Email</small> <span
						class="app-shortcuts__helper bg-teal"></span>
					</a> <a class="col-4 app-shortcuts__item" href=""> <i
						class="zmdi zmdi-trending-up"></i> <small class="">Reports</small>
						<span class="app-shortcuts__helper bg-blue-grey"></span>
					</a> <a class="col-4 app-shortcuts__item" href=""> <i
						class="zmdi zmdi-view-headline"></i> <small class="">News</small>
						<span class="app-shortcuts__helper bg-orange"></span>
					</a> <a class="col-4 app-shortcuts__item" href=""> <i
						class="zmdi zmdi-image"></i> <small class="">Gallery</small> <span
						class="app-shortcuts__helper bg-light-green"></span>
					</a>
                </div>
            </div>
        </li>
        <li class="dropdown hidden-xs-down"><a href="" data-toggle="dropdown"><i class="zmdi zmdi-more-vert"></i></a>
            <div class="dropdown-menu dropdown-menu-right">
                <div class="dropdown-item theme-switch">
                    Theme Switch
                    <div class="btn-group btn-group-toggle btn-group--colors" data-toggle="buttons">
                        <label class="btn bg-green"><input type="radio" value="green" autocomplete="off" checked></label>
                        <label class="btn bg-blue"><input type="radio" value="blue" autocomplete="off"></label>
                        <label class="btn bg-black"><input type="radio" value="black" autocomplete="off"></label>
						<label class="btn bg-orange"><input type="radio" value="orange" autocomplete="off"></label>
						<label class="btn bg-teal"><input type="radio" value="teal" autocomplete="off"></label>
                    <div class="clearfix mt-2"></div>
                        <label class="btn bg-red"><input type="radio" value="red" autocomplete="off"></label>
                       	<label class="btn bg-blue-grey"><input type="radio" value="blue-grey" autocomplete="off"></label>
                       	<label class="btn bg-purple"><input type="radio" value="purple" autocomplete="off"></label>
                       	<label class="btn bg-indigo"><input type="radio" value="indigo" autocomplete="off"></label>
						<label class="btn bg-brown"><input type="radio" value="brown" autocomplete="off"></label>
                    </div>
                </div>
                <a onclick="makeFullScreen();" class="dropdown-item">Fullscreen</a>
                <a onclick="clearTheme();" class="dropdown-item">Clear Local Storage</a>
            </div>
        </li>
        <li class="dropdown hidden-xs-down"><a href="logout" data-toggle="tooltip" data-placement="bottom" title="sign out"><i
				class="fas fa-sign-out-alt fa-lg"></i></a></li>
    </ul>
</header>