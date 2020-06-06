#!/bin/sh
exec scala "$0" "$@"
!#

import javax.swing.JFrame

object GUIHellowWorld extends App {
  val f = new JFrame
  f.setVisible(true)
  f.setTitle("Hello, world!")
  f.setSize(300, 200)
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
}
