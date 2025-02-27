# Change Log
All notable changes to this project will be documented in this file. This change log follows the conventions of [keepachangelog.com](http://keepachangelog.com/).

## [2-alpha28]
- minor aesthetic changes

## [2-alpha27] - 2023-06-07
- leaflet support
- more flexible caching (WIP)
- minor changes to info-line
- rendering more parts as plain-html 
- moved the `is->` function to the kindly-default library

## [2-alpha26] - 2023-06-04
- simpler and more robust path handling in info-line

## [2-alpha25] - 2023-06-04
- catching a possible Exception in the method to recognize the current file

## [2-alpha24] - 2023-06-04
- added souce info-line to the generated doc'

## [2-alpha23] - 2023-05-30
- clojute.test support

## [2-alpha22] - 2023-04-25
- 3dmol support

## [2-alpha21] - 2023-04-24
- passing :kind/code more directly to quarto for better rendering
- bugfix: bringing back the handling of :kind/hiccup, which was dropped by mistake

## [2-alpha20] - 2023-04-24
- minor API extension: handling a value
- allowing to hide code by value metadata
- handling :kind/void
- passing :kind/md more directly to quarto for better rendering
- avoiding recursion in API calls

## [2-alpha19] - 2023-04-21
- Katex support

## [2-alpha18] - 2023-04-21
- handling hidden code blocks differently in the quarto pathway, to avoid quarto slowness
- added Emmy, tmdjs support

## [2-alpha17] - 2023-04-16
- ignoring missing source maps till we figure this problem out
- plotly support, customizable echarts element height
- serving binary files correctly

## [2-alpha16] - 2023-04-06
- simplified the way forms are sent to Clay (avoiding `tap>`)
- escaping printed values for html

## [2-alpha15] - 2023-04-05
- experimental quarto support
- using the Scicloj Scittle fork for MathBox.cljs support
- a more graceful user experience around main actions
- updated API entry points for a few of the main actions

## [2-alpha14]  - 2023-02-21
- table view support for datasets

## [2-alpha13]  - 2023-02-20
- supporting :kind/vega-lite 
- using markdown again when printing datasets (following fix https://github.com/nextjournal/markdown/issues/12)

## [2-alpha12]  - 2022-12-17
- more consistent choice of ports

## [2-alpha11]  - 2022-12-16
- extended the API with a few url-related functions
- updated some client-side deps

## [2-alpha10]  - 2022-12-08
- making sure the selected communication port is free

## [2-alpha9]  - 2022-11-10
- adapting to Kindly changes - returning multiple contexts
- bugfix: using fallback viewer

## [2-alpha8]  - 2022-11-06
- updated Kindly deps

## [2-alpha7]  - 2022-10-30
- temporarily avoiding some problems with dataset rendering

## [2-alpha6]  - 2022-10-29
- adapting to the extraction of kindly-default out of kindly

## [2-alpha5]  - 2022-10-27
- kindly version update

## [2-alpha4]  - 2022-10-23
- more careful kind handling in view
- namespace cleanup & refactoring
- added show-doc-and-write-html! to api
- bugfix in some reader edge cases
- make pprint the fallback case again
- kindly update

## [2-alpha3] - 2022-10-17
- adapting to Kindly changes
- minor visual changes

## [2-alpha2] - 2022-10-12
- dropped the cybermonday dependency

## [2-alpha1] - 2022-10-07
- rendeding documents without relying on Clerk
- switched to clay.v2 namespaces
- switched to kindly v3

## [1-alpha15] - 2022-08-07
- catching errors
- pretty printing where appropriate

## [1-alpha14] - 2022-05-09
- a slight change of the integration with CIDER/Calva (passing more info)
- making certain forms automatically hidden when showing a single value

## [1-alpha13] - 2022-05-06
- using `tap>` rather than nREPL middleware to listen to user evaluations
- fixed the support for code metadata in some cases

## [1-alpha12] - 2022-05-05
- patched clerk to pass code metadata freely
- support for code metadata in scittle doc preparation
- support for extensions setup on start; added dataset, clojisr setup as extensions

## [1-alpha11] - 2022-04-24
- scittle tool: reordered js lib rendering, added visual spacing

## [1-alpha10] - 2022-04-24
- scittle tool: 
  - refactoring of page generation 
  - optional loading of special widgets
  - more self-contained generated html (relying on less web resources)
  - made table-of-contents optional

## [1-alpha9] - 2022-04-23
- various aesthetics changes: bootswatch, tables, tech.ml.dadaset datasets, markdown, table-of-contents, etc.

## [1-alpha8] - 2022-04-22
- making sure kind/hidden is defined

## [1-alpha7] - 2022-04-22
- updated clerk version
- handling naive printing of values better

## [1-alpha6] - 2022-04-21
- updated scittle version
- more customizable document rendering
- more sensible table rendering

## [1-alpha5] - 2022-04-18
- fixed handling delays on scittle document rendering

## [1-alpha4] - 2022-04-17
- various changes in styling & minor API extensions
- Scittle document rendering

## [1-alpha3] - 2022-04-17
minor API extension

## [1-alpha2] - 2022-04-17
initial version of scittle viewer

## [1-alpha1] - 2022-04-08
initial version
    
