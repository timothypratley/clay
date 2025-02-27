;; # Clay

^:kindly/hide-code?
(with-meta
  [:img
   {:style {:width "300px"}
    :src "https://upload.wikimedia.org/wikipedia/commons/2/2c/Clay-ss-2005.jpg"
    :alt "quaternary clay in Estonia"}]
  {:kindly/kind :kind/hiccup})
;; (credit: [Wikimedia Commons](https://commons.wikimedia.org/wiki/File:Clay-ss-2005.jpg))

;; ## What is it?

;; [Clay](https://github.com/scicloj/clay) is a minimalistic Clojure tool for data visualization and literate programming, compatible with the [Kindly](https://scicloj.github.io/kindly/) convention.
;;
;; Also see the [Babashka Conf 2023 talk](https://www.youtube.com/watch?v=HvhMsv3iVGM).

;; ### Goals

;; - Easily explore & share things for others to easily pick & use.
;; - Encourage writing Kindly-compatible notes for future compatiblity with other tools.
;; - Flow with the REPL: encourage user interactions that flow naturally with the typical use of Clojure in editors and REPLs.

;; ## Setup

;; For rendering documents like this one with Clay, you need to:
;; - add [kindly-default](https://clojars.org/org.scicloj/kindly-default) and [Clay](https://clojars.org/org.scicloj/clay) as dependencies
;; - call `(kindly-default/setup!)` and `(clay/start!)` (see below)

;;
;; To enjoy Clay's dynamic interaction, you also need to inform it about code evaluations. This requires some setup at the your editor.
;;
;; See the suggested setup for popular editors below. If your favourite editor is not supported yet, let us talk and make it work.

;; ### VSCode Calva
;; **(to be updated soon)**
;;
;; Please add the following command to your [`keybindings.json` file](https://code.visualstudio.com/docs/getstarted/keybindings#_advanced-customization) at the VScode setup (you may pick another key, of course). This command would evaluate a piece of code and send the result to be visualized in Clay.

;; ```json
;; {
;;  "key": "ctrl+shift+enter",
;;  "command": "calva.runCustomREPLCommand",
;;  "args": "(scicloj.clay.v2.api/handle-form! (quote $current-form))"
;;  }
;; ```

;; ### Emacs CIDER
;; **(to be updated soon)**
;;
;; Please load [clay.el](https://github.com/scicloj/clay/blob/main/clay.el) at your Emacs config.
;;
;; It offers the following functions, that you may wish to create keybindings for:
;;
;; |name|function|
;; |--|--|
;; |`clay/start`|start clay if not started yet|
;; |`clay/show-namespace`|save clj buffer and render it in the browser view|
;; |`clay/show-namespace-and-write-html`|save clj buffer, render it in the browser view, and save the result as html|
;; |`clay/generate-and-show-namespace-quarto`|(experimental) save clj buffer, generate an html document using Quarto, and show it in the browser view|
;; |`clay/send`|send a single clj form to be rendered in the browser view|
;; |`clay/send-last-sexp`|send the last s-expression|
;; |`clay/send-defun-at-point`|send the [defun-at-point](https://www.emacswiki.org/emacs/ThingAtPoint)|


;; ## Starting a Clay namespace

;; Now, we can write a namespace and play with Clay.

(ns index
  (:require [scicloj.clay.v2.api :as clay]
            [scicloj.kindly.v3.api :as kindly]
            [scicloj.kindly.v3.kind :as kind]
            [scicloj.kindly-default.v1.api :as kindly-default]))

(defonce memoized-slurp
  (memoize slurp))

;; Initialize Kindly's [default](https://github.com/scicloj/kindly-default).
(kindly-default/setup!)

;; Let us start Clay.

(clay/start!)

(clay/swap-options!
 assoc
 :remote-repo {:git-url "https://github.com/scicloj/clay"
               :branch "main"})

;; These initializations can also be done in a `user.clj` file, making them available for all namespaces in the project.

;; The browser view should open automatically.

;; ## A few useful actions

;; Showing the whole namespace:
(comment
  (clay/show-doc! "notebooks/index.clj"))

;; Writing the document:
(comment
  (clay/show-doc-and-write-html!
   "notebooks/index.clj"
   {:toc? true}))

;; Reopening the Clay view in the browser (in case you closed the browser tab previously opened by `clay/start!`)
(comment
  (clay/browse!))

;; These can be conveniently bound to functions and keys at your editor (to b documented soon).

;; ## Interaction

;; Clay responds to user evaluations by displaying the result visually.

(+ 1111 2222)

;; In Emacs CIDER, after evaluation of a form (or a region),
;; the browser view should show the evaluation result.

;; In VSCode Calva, a similar effect can be achieved
;; using the dedicated command and keybinding defined above.

;; ## Kinds

;; The way things should be visualized is determined by the advice of
;; [Kindly](https://github.com/scicloj/kindly).

;; In this namespace we demonstrate Kindly's default advice.
;; User-defined Kindly advices should work as well.

;; Kindly advises tools (like Clay) about the kind of way a given context
;; should be displayed, by assigning to it a so-called kind.

;; Please refer to the Kindly documentation for details about specifying
;; and using kinds.

;; ## Examples

;; ### Plain values

;; By default, when there is no kind information provided by Kindly,
;; values are pretty-printed.

(def people-as-maps
  (->> (range 29)
       (mapv (fn [i]
               {:preferred-language (["clojure" "clojurescript" "babashka"]
                                     (rand-int 3))
                :age (rand-int 100)}))))

(def people-as-vectors
  (->> people-as-maps
       (mapv (juxt :preferred-language :age))))

people-as-maps

people-as-vectors

;; ### Pretty printing

;; With the the `:kind/pprint` kind, this can behaviour can be made explicit (overriding other inferred kinds if necessary).
(kind/pprint people-as-maps)

(kind/pprint people-as-vectors)

;; ### Datasets

;; [tech.ml.dataset](https://github.com/techascent/tech.ml.dataset) datasets currently use the default printing of the library,

;; Let us create such a dataset using [Tablecloth](https://github.com/scicloj/tablecloth).

(require '[tablecloth.api :as tc])

(-> {:x (range 6)
     :y [:A :B :C :A :B :C]}
    tc/dataset)

(-> {:x [1 [2 3] 4]
     :y [:A :B :C]}
    tc/dataset)

(-> [{:x 1 :y 2 :z 3}
     {:y 4 :z 5}]
    tc/dataset)

(-> people-as-maps
    tc/dataset)

;; ### Tables

;; The `:kind/table` kind can be handy for an interactive table view.

(kind/table
 {:column-names [:preferred-language :age]
  :row-vectors people-as-vectors})

(kind/table
 {:column-names [:preferred-language :age]
  :row-maps people-as-maps})

(-> people-as-maps
    tc/dataset
    kind/table)

;; ### Hiccup

;; [Hiccup](https://github.com/weavejester/hiccup), a popular Clojure way to represent HTML, can be specified by kind:

(kind/hiccup
 [:big [:big [:p {:style ; https://www.htmlcsscolor.com/hex/7F5F3F
                  {:color "#7F5F3F"}}
              "hello"]]])

;; ### Images

(import javax.imageio.ImageIO
        java.net.URL)

(defonce image
  (->  "https://upload.wikimedia.org/wikipedia/commons/2/2c/Clay-ss-2005.jpg"
       (URL.)
       (ImageIO/read)))

image

;; ### [Vega](https://vega.github.io/vega/) and [Vega-Lite](https://vega.github.io/vega-lite/)

(defn vega-lite-point-plot [data]
  (-> {:data {:values data},
       :mark "point"
       :encoding
       {:size {:field "w" :type "quantitative"}
        :x {:field "x", :type "quantitative"},
        :y {:field "y", :type "quantitative"},
        :fill {:field "z", :type "nominal"}}}
      kind/vega-lite))

(defn random-data [n]
  (->> (repeatedly n #(- (rand) 0.5))
       (reductions +)
       (map-indexed (fn [x y]
                      {:w (rand-int 9)
                       :z (rand-int 9)
                       :x x
                       :y y}))))

(defn random-vega-lite-plot [n]
  (-> n
      random-data
      vega-lite-point-plot))

(random-vega-lite-plot 9)

;; ### [Cytoscape.js](https://js.cytoscape.org/)

(def cytoscape-example
  {:elements {:nodes [{:data {:id "a" :parent "b"} :position {:x 215 :y 85}}
                      {:data {:id "b"}}
                      {:data {:id "c" :parent "b"} :position {:x 300 :y 85}}
                      {:data {:id "d"} :position {:x 215 :y 175}}
                      {:data {:id "e"}}
                      {:data {:id "f" :parent "e"} :position {:x 300 :y 175}}]
              :edges [{:data {:id "ad" :source "a" :target "d"}}
                      {:data {:id "eb" :source "e" :target "b"}}]}
   :style [{:selector "node"
            :css {:content "data(id)"
                  :text-valign "center"
                  :text-halign "center"}}
           {:selector "parent"
            :css {:text-valign "top"
                  :text-halign "center"}}
           {:selector "edge"
            :css {:curve-style "bezier"
                  :target-arrow-shape "triangle"}}]
   :layout {:name "preset"
            :padding 5}})

(kind/cytoscape cytoscape-example)

(kind/cytoscape [cytoscape-example
                 {:style {:height 100
                          :width 100}}])

;; ### [Apache Echarts](https://echarts.apache.org/)

(kind/echarts
 {:xAxis {:data ["Mon" "Tue" "Wed" "Thu" "Fri" "Sat" "Sun"]}
  :yAxis {}
  :series [{:type "bar"
            :color ["#7F5F3F"]
            :data [23 24 18 25 27 28 25]}]})

;; ### MathBox.cljs

;; inspired by [the official MathBox.cljs tutorial](https://mathbox.mentat.org/)
(kind/hiccup
 '[(let [Data (fn []
                [mathbox.primitives/Interval
                 {:expr (fn [emit x _i t]
                          (emit x (Math/sin (+ x t))))
                  :width 64
                  :channels 2}])
         Curve (fn []
                 [:<>
                  [Data]
                  [mathbox.primitives/Line {:width 5
                                            :color "#3090FF"}]])
         Main (fn []
                [mathbox.core/MathBox
                 {:container {:style {:height "400px" :width "100%"}}
                  :focus 3}
                 [mathbox.primitives/Camera {:position [0 0 3]
                                             :proxy true}]
                 [mathbox.primitives/Cartesian
                  {:range [[-2 2] [-1 1]]
                   :scale [2 1]}
                  [mathbox.primitives/Axis {:axis 1 :width 3 :color "black"}]
                  [mathbox.primitives/Axis {:axis 2 :width 3 :color "black"}]
                  [mathbox.primitives/Grid {:width 2 :divideX 20 :divideY 10}]
                  [Curve]]])
         *state (r/atom {:open? false})]
     (fn []
       [:div
        [:button {:on-click #(swap! *state update :open? not)}
         (if (:open? @*state)
           "close"
           "open")]
        (when (:open? @*state)
          [Main])]))])

;; ### plotly
(kind/hiccup
 '[plotly
   {:data [{:x [0 1 3 2]
            :y [0 6 4 5]
            :z [0 8 9 7]
            :type :scatter3d
            :mode :lines+markers
            :opacity 0.5
            :line {:width 5}
            :marker {:size 4
                     :colorscale :Viridis}}]}])

;; ### Katex
(kind/hiccup
 '[katex "1+x^2"])

;; ### Emmy
(kind/hiccup
 '(require '[emmy.env :as e :refer [D]]))

(kind/hiccup
 [:div
  '(-> 'x
       ((D e/cube))
       e/simplify
       e/->infix)])

(kind/hiccup
 '[katex
   (-> 'x
       ((D e/cube))
       e/simplify
       e/->infix)])

;; ### tmdjs
(kind/hiccup
 '(require '[tech.v3.dataset :as tmd]))

(kind/hiccup
 '[(fn []
     [:div
      (-> {:x [1 2 3]}
          tmd/->dataset
          :x
          pr-str)])])


;; ### 3dmol

;; (experimental)

(kind/hiccup
 ['three-d-mol-viewer
  {:data-pdb "2POR"}])

(kind/hiccup
 ['(fn [{:keys [pdb-data]}]
     [:div
      {:style {:width "100%"
               :height "500px"
               :position "relative"}
       :ref (fn [el]
              (let [config (clj->js
                            {:backgroundColor "0xffffff"})
                    viewer (.createViewer js/$3Dmol el #_config)]
                (.setViewStyle viewer (clj->js
                                       {:style "outline"}))
                (.addModelsAsFrames viewer pdb-data "pdb")
                (.addSphere viewer (clj->js
                                    {:center {:x 0
                                              :y 0
                                              :z 0}
                                     :radius 1
                                     :color "green"}))
                (.zoomTo viewer)
                (.render viewer)
                (.zoom viewer 0.8 2000)))}
      ;; need to keep this symbol to let Clay infer the necessary dependency
      'three-d-mol])
  {:pdb-data (memoized-slurp "https://files.rcsb.org/download/2POR.pdb")}])

;; ### Leaflet

(kind/hiccup
 ['(fn []
     [:div
      {:style {:width "100%"
               :height "500px"}
       :ref (fn [el]
              (let [m (-> js/L
                          (.map el)
                          (.setView (clj->js [52.5274319 13.4004289])
                                    19))]
                (-> js/L
                    (.tileLayer "https://tile.openstreetmap.org/{z}/{x}/{y}.png"
                                (clj->js {:maxZoom 19
                                          :attribution "&copy; <a href=\"http://www.openstreetmap.org/copyright\">OpenStreetMap</a>"}))
                    (.addTo m))))}
      'leaflet])])


;; ## Delays

;; Clojure Delays are a common way to define computations that do not take place immediately. The computation takes place when dereferencing the value for the first time.

;; Clay makes sure to dererence Delays when passing values for visualization.

;; This is handy for slow example snippets and explorations, that one would typically not like to slow down the evaluation of the whole namespace, but would like to visualize them on demand and also include in them in the final document.

(delay
  (Thread/sleep 500)
  (+ 1 2))

(delay
  [:div [:big "hi......."]])

;; ## Tests

;; ### clojure.test

;; Clay offers a few features supporting the use of standard Clojure tests.

(require '[clojure.test :refer [deftest is]]
         '[scicloj.kindly-default.v1.api :refer [is->]])

;; Tests returning a boolean value (as they usually do, ending with a check)
;; are rendered displaying that value as a clear x (failure) or v (success) mark:

(def test-dataset
  (tc/dataset {:x [1 2 3]
               :y [4 5 6]}))

(deftest mytest1
  (-> test-dataset
      tc/row-count
      (= 3)
      is))

;; Tests returning a non-boolean value are rendered simply displaying that value:

(deftest mytest2
  (-> test-dataset
      tc/row-count
      (= 3)
      is)
  test-dataset)

;; The `is->` function allows performing a few checks in a pipeline
;; and returning a different value to be displayed:

(deftest mytest3
  (-> 2
      (+ 3)
      (is-> > 4)
      (* 10)
      (is-> = 50)
      (* 10)))

;; These features open the way for literate testing / testable documentation solutions, such as those we have been using in the past (e.g., in [tutorials](https://scicloj.github.io/clojisr/doc/clojisr/v1/tutorial-test/) of ClojisR using Notespace v2).
