package render;

// kommentarrr
import game.Game;
import game.Level;
import game.Player;
import game.ThreadBomb;
import render.Objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * Das Optionsmenue, in dem die Einstellungen für das Spiel vorgenommen werden können.
 *
 */
public class Menu extends javax.swing.JFrame {
	public static boolean menuOffen;

	public Menu() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	
	/**
	 * Implementiert die Menueobjekte.
	 */
	private void initComponents() {

		gTheme = new javax.swing.ButtonGroup();
		lOptionen = new javax.swing.JLabel();
		bEnde = new javax.swing.JButton();
		pLevel = new javax.swing.JPanel();
		lWidth = new javax.swing.JLabel();
		sLevelZ = new javax.swing.JSlider();
		sLevelX = new javax.swing.JSlider();
		sLevelY = new javax.swing.JSlider();
		bGravity = new javax.swing.JToggleButton();
		lDepth = new javax.swing.JLabel();
		lHeight = new javax.swing.JLabel();
		rThemeEarth = new javax.swing.JRadioButton();
		rThemeSpace = new javax.swing.JRadioButton();
		rThemeEM = new javax.swing.JRadioButton();
		lTheme = new javax.swing.JLabel();
		jSeparator1 = new javax.swing.JSeparator();
		sObstacle = new javax.swing.JSlider();
		lObstacle = new javax.swing.JLabel();
		jSeparator3 = new javax.swing.JSeparator();
		pBombs = new javax.swing.JPanel();
		lBeginBombs = new javax.swing.JLabel();
		lBombs = new javax.swing.JLabel();
		sBeginRange = new javax.swing.JSlider();
		sBeginMaxBombs = new javax.swing.JSlider();
		sRange = new javax.swing.JSlider();
		lRange = new javax.swing.JLabel();
		lBeginRange = new javax.swing.JLabel();
		sMaxBombs = new javax.swing.JSlider();
		jSeparator2 = new javax.swing.JSeparator();
		pPlayer = new javax.swing.JPanel();
		spinMouse = new javax.swing.JSpinner();
		lSpeed = new javax.swing.JLabel();
		spinHealth = new javax.swing.JSpinner();
		spinSpeed = new javax.swing.JSpinner();
		lMouse = new javax.swing.JLabel();
		lMaxHealth = new javax.swing.JLabel();
		pItems = new javax.swing.JPanel();
		cHealth = new javax.swing.JCheckBox();
		lTheme1 = new javax.swing.JLabel();
		cRadius = new javax.swing.JCheckBox();
		sItem = new javax.swing.JSlider();
		cBombPlus = new javax.swing.JCheckBox();
		cPortal = new javax.swing.JCheckBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Optionen");
		setName("menu");
		// setType(java.awt.Window.Type.UTILITY);
		setUndecorated(true);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent evt) {
				formWindowOpened(evt);
				scanOptions();
				loadOptions();
			}
		});

		lOptionen.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
		lOptionen.setText("Optionen");

		bEnde.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		bEnde.setText("Speichern und Schließen");
		bEnde.setToolTipText("Speichert die Einstellungen und schließt das Menüfenster.");
		bEnde.setActionCommand("CLOSE");
		bEnde.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bEndeActionPerformed(evt);
			}
		});

		pLevel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Leveleinstellungen:",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Perpetua Titling MT", 1, 10))); // NOI18N

		lWidth.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		lWidth.setText("Breite");

		sLevelZ.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		sLevelZ.setMajorTickSpacing(5);
		sLevelZ.setMinimum(10);
		sLevelZ.setMaximum(30);
		sLevelZ.setMinorTickSpacing(1);
		sLevelZ.setPaintLabels(true);
		sLevelZ.setPaintTicks(true);
		sLevelZ.setSnapToTicks(true);
		sLevelZ.setToolTipText("Die Tiefe des Levels.");
		sLevelZ.setValue(10);

		sLevelX.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		sLevelX.setMajorTickSpacing(5);
		sLevelX.setMinimum(10);
		sLevelX.setMaximum(30);
		sLevelX.setMinorTickSpacing(1);
		sLevelX.setPaintLabels(true);
		sLevelX.setPaintTicks(true);
		sLevelX.setSnapToTicks(true);
		sLevelX.setToolTipText("Die Breite des Levels.");
		sLevelX.setValue(10);

		sLevelY.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		sLevelY.setMajorTickSpacing(5);
		sLevelY.setMinimum(10);
		sLevelY.setMaximum(30);
		sLevelY.setMinorTickSpacing(1);
		sLevelY.setPaintLabels(true);
		sLevelY.setPaintTicks(true);
		sLevelY.setSnapToTicks(true);
		sLevelY.setToolTipText("Die Höhe des Levels.");
		sLevelY.setValue(10);

		bGravity.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		bGravity.setText("Schwerkraft aus");
		bGravity.setToolTipText("Die Schwerkraft im Spiel ist nun ausgeschaltet.");
		bGravity.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bGravityActionPerformed(evt);
			}
		});

		lDepth.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		lDepth.setText("Tiefe");

		lHeight.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		lHeight.setText("Höhe");

		gTheme.add(rThemeEarth);
		rThemeEarth.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		rThemeEarth.setSelected(true);
		rThemeEarth.setText("Erde");
		rThemeEarth.setToolTipText("Die Erde: raues Metall und Wände hart wie Stein!");

		gTheme.add(rThemeSpace);
		rThemeSpace.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		rThemeSpace.setText("Weltraum");
		rThemeSpace.setToolTipText("Im Weltraum wird eine Schlacht zwischen Mond und Mars ausgetragen!");

		gTheme.add(rThemeEM);
		rThemeEM.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		rThemeEM.setText("EM 2012");
		rThemeEM.setToolTipText("Deutschland spielt gegen Holland. Ein unvergleichliches Spiel!");

		lTheme.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		lTheme.setText("Thema wählen:");

		sObstacle.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		sObstacle.setMajorTickSpacing(10);
		sObstacle.setMinorTickSpacing(1);
		sObstacle.setPaintLabels(true);
		sObstacle.setPaintTicks(true);
		sObstacle.setSnapToTicks(true);
		sObstacle.setValue(30);

		lObstacle.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		lObstacle.setText("Häufigkeit der zerstörbaren Blöcke (%)");

		javax.swing.GroupLayout pLevelLayout = new javax.swing.GroupLayout(pLevel);
		pLevel.setLayout(pLevelLayout);
		pLevelLayout
				.setHorizontalGroup(pLevelLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pLevelLayout
										.createSequentialGroup()
										.addGap(33, 33, 33)
										.addGroup(
												pLevelLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																pLevelLayout
																		.createSequentialGroup()
																		.addGroup(
																				pLevelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(rThemeSpace)
																						.addComponent(rThemeEarth))
																		.addContainerGap())
														.addGroup(
																pLevelLayout
																		.createSequentialGroup()
																		.addComponent(rThemeEM)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE).addComponent(bGravity))))
						.addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(sObstacle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(
								pLevelLayout
										.createSequentialGroup()
										.addGroup(
												pLevelLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																pLevelLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				pLevelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								pLevelLayout
																										.createSequentialGroup()
																										.addGap(2, 2, 2)
																										.addGroup(
																												pLevelLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING)
																														.addGroup(
																																pLevelLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				lWidth)
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																		.addComponent(
																																				sLevelX,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				182,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addComponent(
																																sLevelY,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																182,
																																javax.swing.GroupLayout.PREFERRED_SIZE)))
																						.addComponent(lHeight)
																						.addGroup(
																								pLevelLayout
																										.createSequentialGroup()
																										.addComponent(lDepth)
																										.addGap(18, 18, 18)
																										.addComponent(
																												sLevelZ,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												182,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addComponent(lTheme)))
														.addGroup(
																pLevelLayout.createSequentialGroup().addGap(24, 24, 24)
																		.addComponent(lObstacle)))
										.addContainerGap(40, Short.MAX_VALUE))
						.addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING));
		pLevelLayout.setVerticalGroup(pLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				pLevelLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								pLevelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(sLevelX, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(lWidth))
						.addGap(18, 18, 18)
						.addGroup(
								pLevelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												pLevelLayout.createSequentialGroup().addComponent(lHeight).addGap(38, 38, 38)
														.addComponent(lDepth))
										.addGroup(
												pLevelLayout
														.createSequentialGroup()
														.addComponent(sLevelY, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(sLevelZ, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
						.addComponent(lObstacle, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(sObstacle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(8, 8, 8)
						.addComponent(lTheme)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(rThemeEarth)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(rThemeSpace)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(
								pLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(rThemeEM).addComponent(bGravity))));

		sLevelZ.getAccessibleContext().setAccessibleName("sLevelZ");
		sLevelY.getAccessibleContext().setAccessibleName("sLevelY");
		bGravity.getAccessibleContext().setAccessibleName("bGravity");
		rThemeEarth.getAccessibleContext().setAccessibleName("rThemeEarth");
		rThemeSpace.getAccessibleContext().setAccessibleName("rThemeSpace");
		rThemeEM.getAccessibleContext().setAccessibleName("rThemeEM");

		pBombs.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bombeneinstellungen:",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Perpetua Titling MT", 1, 10))); // NOI18N

		lBeginBombs.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		lBeginBombs.setText("Zu Beginn legabre Bombenzahl");

		lBombs.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		lBombs.setText("Maximale Anzahl von Bomben");

		sBeginRange.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		sBeginRange.setMajorTickSpacing(3);
		sBeginRange.setMaximum(10);
		sBeginRange.setMinimum(1);
		sBeginRange.setMinorTickSpacing(1);
		sBeginRange.setPaintLabels(true);
		sBeginRange.setPaintTicks(true);
		sBeginRange.setSnapToTicks(true);
		sBeginRange.setToolTipText("Legt den maximalen Explosionsradius der Bomben zu Beginn des Spiels fest.");
		sBeginRange.setValue(1);

		sBeginMaxBombs.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		sBeginMaxBombs.setMajorTickSpacing(3);
		sBeginMaxBombs.setMaximum(10);
		sBeginMaxBombs.setMinimum(1);
		sBeginMaxBombs.setMinorTickSpacing(1);
		sBeginMaxBombs.setPaintLabels(true);
		sBeginMaxBombs.setPaintTicks(true);
		sBeginMaxBombs.setSnapToTicks(true);
		sBeginMaxBombs
				.setToolTipText("Legt die maximale Anzahl von Bomben fest, die ein Spieler zu Beginn des Spiels legen kann.");
		sBeginMaxBombs.setValue(1);

		sRange.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		sRange.setMajorTickSpacing(3);
		sRange.setMaximum(10);
		sRange.setMinimum(1);
		sRange.setMinorTickSpacing(1);
		sRange.setPaintLabels(true);
		sRange.setPaintTicks(true);
		sRange.setSnapToTicks(true);
		sRange.setToolTipText("Legt den maximalen Explosionsradius der Bomben fest.");
		sRange.setValue(1);

		lRange.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		lRange.setText("Explosionsradius zu Beginn:");

		lBeginRange.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		lBeginRange.setText("Maximaler Explosionsradius:");

		sMaxBombs.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		sMaxBombs.setMajorTickSpacing(1);
		sMaxBombs.setMaximum(10);
		sMaxBombs.setMinimum(1);
		sMaxBombs.setMinorTickSpacing(1);
		sMaxBombs.setPaintLabels(true);
		sMaxBombs.setPaintTicks(true);
		sMaxBombs.setSnapToTicks(true);
		sMaxBombs.setToolTipText("Legt die maximale Anzahl von Bomben fest, die ein Spieler gleichzeitig legen kann.");
		sMaxBombs.setValue(1);

		javax.swing.GroupLayout pBombsLayout = new javax.swing.GroupLayout(pBombs);
		pBombs.setLayout(pBombsLayout);
		pBombsLayout
				.setHorizontalGroup(pBombsLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pBombsLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												pBombsLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																pBombsLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				pBombsLayout
																						.createSequentialGroup()
																						.addComponent(
																								lBombs,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								197,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								sMaxBombs,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								182,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				pBombsLayout
																						.createSequentialGroup()
																						.addGroup(
																								pBombsLayout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING)
																										.addGroup(
																												pBombsLayout
																														.createSequentialGroup()
																														.addGroup(
																																pBombsLayout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.TRAILING)
																																		.addComponent(
																																				lRange)
																																		.addComponent(
																																				lBeginRange))
																														.addGap(18,
																																18,
																																18))
																										.addGroup(
																												pBombsLayout
																														.createSequentialGroup()
																														.addComponent(
																																lBeginBombs,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																197,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addGap(4,
																																4,
																																4)))
																						.addGroup(
																								pBombsLayout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																										.addComponent(
																												sBeginRange,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												182,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												sBeginMaxBombs,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												182,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												sRange,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												182,
																												javax.swing.GroupLayout.PREFERRED_SIZE))))
														.addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 393,
																Short.MAX_VALUE)).addContainerGap()));
		pBombsLayout.setVerticalGroup(pBombsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				pBombsLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								pBombsLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lBombs, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sMaxBombs, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(11, 11, 11)
						.addGroup(
								pBombsLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lBeginBombs, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sBeginMaxBombs, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
						.addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(
								pBombsLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lBeginRange, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sRange, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(11, 11, 11)
						.addGroup(
								pBombsLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lRange, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sBeginRange, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		pPlayer.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Spielereinstellungen:",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Perpetua Titling MT", 1, 10))); // NOI18N

		spinMouse.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		spinMouse.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(50.0f), Float.valueOf(0.0f), Float.valueOf(100.0f),
				Float.valueOf(1.0f)));
		spinMouse
				.setToolTipText("Die Sensibilität bestimmt, wie schnell die Bewegung einer Drehung mit der Maus ausgeführt wird. ein höherer Wert sorgt hier für eine schnellere Drehung.");
		spinMouse.setName("");

		lSpeed.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		lSpeed.setText("Geschwindigkeit:");

		spinHealth.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		spinHealth.setModel(new javax.swing.SpinnerNumberModel(100, 10, 300, 10));
		spinHealth
				.setToolTipText("Die Anzahl der Lebenspunkte, die ein Spieler maximal (z.B. durch Hilfe von Items) erreichen kann.");
		spinHealth.setName("");

		spinSpeed.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		spinSpeed.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(2.0f),
				Float.valueOf(0.1f)));
		spinSpeed.setToolTipText("Die Laufgeschwindigkeit des Spielers.");
		spinSpeed.setName("");

		lMouse.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		lMouse.setText("Maussensibilität:");

		lMaxHealth.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		lMaxHealth.setText("Maximale Lebenspunkte:");

		javax.swing.GroupLayout pPlayerLayout = new javax.swing.GroupLayout(pPlayer);
		pPlayer.setLayout(pPlayerLayout);
		pPlayerLayout.setHorizontalGroup(pPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				pPlayerLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								pPlayerLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lSpeed)
										.addComponent(lMaxHealth)
										.addComponent(lMouse)
										.addGroup(
												pPlayerLayout
														.createSequentialGroup()
														.addGap(190, 190, 190)
														.addGroup(
																pPlayerLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																		.addComponent(spinHealth,
																				javax.swing.GroupLayout.PREFERRED_SIZE, 60,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(spinSpeed,
																				javax.swing.GroupLayout.PREFERRED_SIZE, 60,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(spinMouse,
																				javax.swing.GroupLayout.PREFERRED_SIZE, 60,
																				javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pPlayerLayout.setVerticalGroup(pPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				pPlayerLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								pPlayerLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lMaxHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(spinHealth, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								pPlayerLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(spinSpeed, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								pPlayerLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lMouse, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(spinMouse, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		lSpeed.getAccessibleContext().setAccessibleName("lSpeed");
		spinHealth.getAccessibleContext().setAccessibleName("spinHealth");
		lMouse.getAccessibleContext().setAccessibleName("lMouse");

		pItems.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Items",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Perpetua Titling MT", 1, 10))); // NOI18N

		cHealth.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		cHealth.setSelected(true);
		cHealth.setText("Verbandskasten");

		lTheme1.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		lTheme1.setText("Itemhäufigkeit wählen");

		cRadius.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		cRadius.setSelected(true);
		cRadius.setText("Explosion +");

		sItem.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		sItem.setMajorTickSpacing(10);
		sItem.setPaintLabels(true);
		sItem.setSnapToTicks(true);
		sItem.setValue(30);

		cBombPlus.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		cBombPlus.setSelected(true);
		cBombPlus.setText("Bombe +");

		cPortal.setFont(new java.awt.Font("Perpetua Titling MT", 1, 10)); // NOI18N
		cPortal.setSelected(true);
		cPortal.setText("Portal");

		javax.swing.GroupLayout pItemsLayout = new javax.swing.GroupLayout(pItems);
		pItems.setLayout(pItemsLayout);
		pItemsLayout
				.setHorizontalGroup(pItemsLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pItemsLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(lTheme1).addContainerGap())
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								pItemsLayout
										.createSequentialGroup()
										.addGroup(
												pItemsLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																pItemsLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(sItem,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																pItemsLayout
																		.createSequentialGroup()
																		.addGap(25, 25, 25)
																		.addGroup(
																				pItemsLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(cHealth)
																						.addComponent(cPortal))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				44, Short.MAX_VALUE)
																		.addGroup(
																				pItemsLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(cBombPlus)
																						.addComponent(cRadius))))
										.addGap(34, 34, 34)));
		pItemsLayout.setVerticalGroup(pItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				pItemsLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(lTheme1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
						.addComponent(sItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(
								pItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(cHealth)
										.addComponent(cBombPlus, javax.swing.GroupLayout.Alignment.TRAILING))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(
								pItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(cRadius).addComponent(cPortal)).addContainerGap()));

		cHealth.getAccessibleContext().setAccessibleName("cHealth");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(345, 345, 345).addComponent(lOptionen).addContainerGap())
				.addGroup(
						layout.createSequentialGroup()
								.addGap(26, 26, 26)
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(pItems, javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addGap(246, 246, 246)
																.addComponent(bEnde, javax.swing.GroupLayout.PREFERRED_SIZE,
																		215, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(pLevel, javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(pBombs,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(pPlayer,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)))).addGap(33, 33, 33)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lOptionen)
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												layout.createSequentialGroup()
														.addComponent(pBombs, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addComponent(pPlayer, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(bEnde, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(
												layout.createSequentialGroup()
														.addComponent(pLevel, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(pItems, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGap(12, 12, 12)));

		lOptionen.getAccessibleContext().setAccessibleName("lOptionen");
		bEnde.getAccessibleContext().setAccessibleName("bEnde");

		getAccessibleContext().setAccessibleName("menu");

		pack();
	}//Ende der Methode initComponents()

	private void bEndeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bEndeActionPerformed
		saveOptions();
		// preExecuteOptions();
		this.dispose();
		menuOffen = false;
	}

	/**
	 * Speichert die im Optionsfenster eingestellten Werte in der Datei
	 * Optionen.txt
	 */
	public void saveOptions() {

		File file;
		FileWriter writer;
		file = new File("save/Optionen.txt");
		try {
			writer = new FileWriter(file);

			// 1. - 3. Levelgröße speichern:
			writer.write(sLevelX.getValue() + "");
			writer.write(System.getProperty("line.separator"));
			writer.write(sLevelY.getValue() + "");
			writer.write(System.getProperty("line.separator"));
			writer.write(sLevelZ.getValue() + "");
			writer.write(System.getProperty("line.separator"));
			// 4.Obstaclewahrscheinlichkeit:
			writer.write(sObstacle.getValue() + "");
			writer.write(System.getProperty("line.separator"));
			// 5. Theme speichern:
			if (rThemeEarth.isSelected()) {
				writer.write(Objects.THEME_EARTH + "");
				Objects.themeSelection = Objects.THEME_EARTH;
			} else if (rThemeSpace.isSelected()) {
				writer.write(Objects.THEME_SPACE + "");
				Objects.themeSelection = Objects.THEME_SPACE;
			} else if (rThemeEM.isSelected()) {
				writer.write(Objects.THEME_SOCCER + "");
				Objects.themeSelection = Objects.THEME_SOCCER;
			}
			writer.write(System.getProperty("line.separator"));
			// 6. Schwerkraftsstatus:
			if (bGravity.isSelected()) {
				writer.write("1");
			} else {
				writer.write("0");
			}
			writer.write(System.getProperty("line.separator"));
			// 7. Itemhäufigkeit:
			writer.write(sItem.getValue() + "");
			writer.write(System.getProperty("line.separator"));
			// 8. - 11. Itemvorhandensein speichern:
			if (cHealth.isSelected()) {
				writer.write("1");
			} else
				writer.write("0");
			writer.write(System.getProperty("line.separator"));
			if (cPortal.isSelected()) {
				writer.write("1");
			} else
				writer.write("0");
			writer.write(System.getProperty("line.separator"));
			if (cBombPlus.isSelected())
				writer.write("1");
			else
				writer.write("0");
			writer.write(System.getProperty("line.separator"));
			if (cRadius.isSelected())
				writer.write("1");
			else
				writer.write("0");
			writer.write(System.getProperty("line.separator"));
			// 12. Maximale Bombenzahl
			writer.write(sMaxBombs.getValue() + "");
			writer.write(System.getProperty("line.separator"));
			// 13. Bomben zu Beginn
			writer.write(sBeginMaxBombs.getValue() + "");
			writer.write(System.getProperty("line.separator"));
			// 14. Maximaler Sprengradius
			writer.write(sRange.getValue() + "");
			writer.write(System.getProperty("line.separator"));
			// 15. Sprengradius zu Beginn
			writer.write(sBeginRange.getValue() + "");
			writer.write(System.getProperty("line.separator"));
			// Maximale Hitpoints
			int parse = (Integer) spinHealth.getValue();
			writer.write(parse+"");
			writer.write(System.getProperty("line.separator"));
			//Geschwindigkeit
			writer.write(spinSpeed.getValue()+"");
			writer.write(System.getProperty("line.separator"));
			//Maussensibilität
			writer.write(spinMouse.getValue()+"");
			writer.write(System.getProperty("line.separator"));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		scanOptions();
		initializeOptions();
	}

	/**
	 * Setzt die Werte im Programm
	 */
	public static void initializeOptions() {
		Game.LEVEL_SIZE_X = Integer.parseInt(Game.options[0]);
		Game.LEVEL_SIZE_Y = Integer.parseInt(Game.options[1]);
		Game.LEVEL_SIZE_Z = Integer.parseInt(Game.options[2]);
		Level.OBSTACLE_PROBABILITY = Float.parseFloat(Game.options[3]) / 100;
		ThreadBomb.ITEM_PROBABLY = Float.parseFloat(Game.options[6]) / 300; // 300
																			// =
																			// 100
																			// (für
																			// Float)
																			// *
																			// 6
																			// (für
																			// Anzahl
																			// der
																			// Items/2)
		if (Game.options[7].equals("1")) {
			ThreadBomb.HealthOn = 1;
		} else {
			ThreadBomb.HealthOn = 0;
		}
		if (Game.options[8].equals("1")) {
			ThreadBomb.PortalOn = 1;
		} else {
			ThreadBomb.PortalOn = 0;
		}
		if (Game.options[9].equals("1")) {
			ThreadBomb.XtraBombOn = 1;
		} else {
			ThreadBomb.XtraBombOn = 0;
		}
		if (Game.options[10].equals("1")) {
			ThreadBomb.BombRangeOn = 1;
		} else {
			ThreadBomb.BombRangeOn = 0;
		}
		
		Player.MAX_SIMULTAN_BOMBS = Integer.parseInt(Game.options[11]);
		if(Integer.parseInt(Game.options[12])<Integer.parseInt(Game.options[11])){
			Player.START_BOMBS = Integer.parseInt(Game.options[12]);
		}else{
			Player.START_BOMBS = Integer.parseInt(Game.options[11]);
		}
		Player.MAX_BOMB_RADIUS = Integer.parseInt(Game.options[13]);
		if (Integer.parseInt(Game.options[14]) < Integer.parseInt(Game.options[13])) {
			Player.radius = Integer.parseInt(Game.options[14]);
		} else {
			Player.radius = Integer.parseInt(Game.options[13]);
		}
		Player.MAX_HEALTH_POINTS = Integer.parseInt(Game.options[15]);
    	Player.stepSize = Float.parseFloat(Game.options[16]);

	}

	/**
	 * Setzt Werte im Programm, die erst zu einem späteren Zeitpunkt auftreten
	 * dürfen (Bsp: Schwerkraft im Menue funktioniert nicht)
	 */
	public static void postInitialize() {
		if (Game.options[5].equals("1")) {
			Player.GRAVITY = true;
		} else {
			Player.GRAVITY = false;
		}

	}

	public static void scanOptions() {
		File file;
		file = new File("save/Optionen.txt");
		if(file.exists()){
		try {
			Scanner scanner = new Scanner(file);
			for (int i = 0; i < Game.options.length - 1; i++) {
				Game.options[i] = scanner.nextLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		}else{
			System.out.println("Die Datei Optionen.txt konnte nicht gefunden werden. Sie wurde mit den Standardeinstellungen erstellt.");
			optionsRescue();
		}

	}

	/**
	 * Lädt die Einstellungen aus dem Array "Game.options" werden auf das
	 * Fenster übertragen, sodass die Werte den eingestellten Werten
	 * entsprechen.
	 * 
	 * Reihenfolge:
	 * 0.-2. Levelgrösse (x,y,z)
	 * 3. Obstacle-Wahrscheinlichkeit
	 * 4. Theme (0 = Earth, 1 = Space, 2 = Fussball)
	 * 5. Schwerkraft (1 = ein, 0 = aus)
	 * 6. Itemhäufigkeit
	 * 7.-10. Vorhandensein folgender Items (1 = Ja, 0 = Nein): Health, Portal, Bomb+, Range+
	 * 11. maximale Bombenzahl
	 * 12. Bombenzahl zu Beginn
	 * 13. maximale Range
	 * 14. Range zu Beginn
	 * 15. maximale Lebenspunkte
	 * 16. Spielergeschwindigkeit
	 * 17. Maussensibilität
	 */
	public static void loadOptions() {
		sLevelX.setValue(Integer.parseInt(Game.options[0]));
		sLevelY.setValue(Integer.parseInt(Game.options[1]));
		sLevelZ.setValue(Integer.parseInt(Game.options[2]));
		sObstacle.setValue(Integer.parseInt(Game.options[3]));

		if (Byte.parseByte(Game.options[4]) == Objects.THEME_EARTH) {
			rThemeEarth.setSelected(true);
		} else if (Byte.parseByte(Game.options[4]) == Objects.THEME_SPACE) {
			rThemeSpace.setSelected(true);
		} else if (Byte.parseByte(Game.options[4]) == Objects.THEME_SOCCER) {
			rThemeEM.setSelected(true);
		}
		if (Integer.parseInt(Game.options[5]) == 1) {
			bGravity.setSelected(true);
			bGravity.setText("Schwerkraft ein");
		} else {
			bGravity.setSelected(false);
			bGravity.setText("Schwerkraft aus");
		}
		sItem.setValue(Integer.parseInt(Game.options[6]));
		if (Integer.parseInt(Game.options[7]) == 1) {
			cHealth.setSelected(true);
		} else {
			cHealth.setSelected(false);
		}
		if (Integer.parseInt(Game.options[8]) == 1) {
			cPortal.setSelected(true);
		} else {
			cPortal.setSelected(false);
		}
		if (Integer.parseInt(Game.options[9]) == 1) {
			cBombPlus.setSelected(true);
		} else {
			cBombPlus.setSelected(false);
		}
		if (Integer.parseInt(Game.options[10]) == 1) {
			cRadius.setSelected(true);
		} else {
			cRadius.setSelected(false);
		}
		sMaxBombs.setValue(Integer.parseInt(Game.options[11]));
		sBeginMaxBombs.setValue(Integer.parseInt(Game.options[12]));
		sRange.setValue(Integer.parseInt(Game.options[13]));
		sBeginRange.setValue(Integer.parseInt(Game.options[14]));
		spinHealth.setValue(Integer.parseInt((Game.options[15])));
		spinSpeed.setValue(Float.parseFloat(Game.options[16]));
		spinMouse.setValue(Float.parseFloat(Game.options[17]));
	}

	/**
	 * Schützt das Programm vor einem Absturz, indem eine neue Datei "Optionen.txt" mit Standardeinstellungen erzeugt wird..
	 */
    public static void optionsRescue(){
		File file;
		FileWriter writer;
		file = new File("save/Optionen.txt");
		try {
			writer = new FileWriter(file);
			writer.write("10");
			writer.write(System.getProperty("line.separator"));
			writer.write("10");
			writer.write(System.getProperty("line.separator"));
			writer.write("10");
			writer.write(System.getProperty("line.separator"));
			writer.write("0");
			writer.write(System.getProperty("line.separator"));
			writer.write("0");
			writer.write(System.getProperty("line.separator"));
			writer.write("0");
			writer.write(System.getProperty("line.separator"));
			writer.write("100");
			writer.write(System.getProperty("line.separator"));
			writer.write("1");
			writer.write(System.getProperty("line.separator"));
			writer.write("1");
			writer.write(System.getProperty("line.separator"));
			writer.write("1");
			writer.write(System.getProperty("line.separator"));
			writer.write("1");
			writer.write(System.getProperty("line.separator"));
			writer.write("5");
			writer.write(System.getProperty("line.separator"));
			writer.write("1");
			writer.write(System.getProperty("line.separator"));
			writer.write("5");
			writer.write(System.getProperty("line.separator"));
			writer.write("1");
			writer.write(System.getProperty("line.separator"));
			writer.write("100");
			writer.write(System.getProperty("line.separator"));
			writer.write("1");
			writer.write(System.getProperty("line.separator"));
			writer.write("50");
			writer.write(System.getProperty("line.separator"));
			writer.flush();
			writer.close();
			scanOptions();
			initializeOptions();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	private void formWindowOpened(java.awt.event.WindowEvent evt) {
		// Daten aus Datei einlesen und alle Regler dementsprechend einsellen.
		// Daten im Programm ändern.
	}
	/**
	 * Gute Effekte des Gravity-Buttons
	 * @param evt
	 */
	private void bGravityActionPerformed(java.awt.event.ActionEvent evt) {
		if (bGravity.isSelected()) {
			bGravity.setText("Schwerkraft ein");
			bGravity.setToolTipText("Die Schwerkraft im Spiel ist nun eingeschaltet.");
		} else {
			bGravity.setText("Schwerkraft aus");
			bGravity.setToolTipText("Die Schwerkraft im Spiel ist nun ausgeschaltet.");
		}
	}

	// Variables declaration - do not modify
	public static javax.swing.JButton bEnde;
	public static javax.swing.JToggleButton bGravity;
	public static javax.swing.JCheckBox cBombPlus;
	public static javax.swing.JCheckBox cHealth;
	public static javax.swing.JCheckBox cPortal;
	public static javax.swing.JCheckBox cRadius;
	public static javax.swing.ButtonGroup gTheme;
	public static javax.swing.JSeparator jSeparator1;
	public static javax.swing.JSeparator jSeparator2;
	public static javax.swing.JSeparator jSeparator3;
	public static javax.swing.JLabel lBeginBombs;
	public static javax.swing.JLabel lBeginRange;
	public static javax.swing.JLabel lBombs;
	public static javax.swing.JLabel lDepth;
	public static javax.swing.JLabel lHeight;
	public static javax.swing.JLabel lMaxHealth;
	public static javax.swing.JLabel lMouse;
	public static javax.swing.JLabel lObstacle;
	public static javax.swing.JLabel lOptionen;
	public static javax.swing.JLabel lRange;
	public static javax.swing.JLabel lSpeed;
	public static javax.swing.JLabel lTheme;
	public static javax.swing.JLabel lTheme1;
	public static javax.swing.JLabel lWidth;
	public static javax.swing.JPanel pBombs;
	public static javax.swing.JPanel pItems;
	public static javax.swing.JPanel pLevel;
	public static javax.swing.JPanel pPlayer;
	public static javax.swing.JRadioButton rThemeEM;
	public static javax.swing.JRadioButton rThemeEarth;
	public static javax.swing.JRadioButton rThemeSpace;
	public static javax.swing.JSlider sBeginMaxBombs;
	public static javax.swing.JSlider sBeginRange;
	public static javax.swing.JSlider sItem;
	public static javax.swing.JSlider sLevelX;
	public static javax.swing.JSlider sLevelY;
	public static javax.swing.JSlider sLevelZ;
	public static javax.swing.JSlider sMaxBombs;
	public static javax.swing.JSlider sObstacle;
	public static javax.swing.JSlider sRange;
	public static javax.swing.JSpinner spinHealth;
	public static javax.swing.JSpinner spinMouse;
	public static javax.swing.JSpinner spinSpeed;
	// End of variables declaration//GEN-END:variables
}
