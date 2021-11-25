import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { Translate, translate } from 'react-jhipster';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    data-cy="entity"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <>{/* to avoid warnings when empty */}</>
    <MenuItem icon="asterisk" to="/member">
      <Translate contentKey="global.menu.entities.member" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/language">
      <Translate contentKey="global.menu.entities.language" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/subject">
      <Translate contentKey="global.menu.entities.subject" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/type">
      <Translate contentKey="global.menu.entities.type" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/event">
      <Translate contentKey="global.menu.entities.event" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/m-log">
      <Translate contentKey="global.menu.entities.mLog" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/attachment">
      <Translate contentKey="global.menu.entities.attachment" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/schedule">
      <Translate contentKey="global.menu.entities.schedule" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/feedback">
      <Translate contentKey="global.menu.entities.feedback" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
