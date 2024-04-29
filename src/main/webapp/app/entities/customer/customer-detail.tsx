import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './customer.reducer';

export const CustomerDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const customerEntity = useAppSelector(state => state.customer.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="customerDetailsHeading">
          <Translate contentKey="wayThereJhipsterApp.customer.detail.title">Customer</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{customerEntity.id}</dd>
          <dt>
            <span id="customerID">
              <Translate contentKey="wayThereJhipsterApp.customer.customerID">Customer ID</Translate>
            </span>
          </dt>
          <dd>{customerEntity.customerID}</dd>
          <dt>
            <span id="fname">
              <Translate contentKey="wayThereJhipsterApp.customer.fname">Fname</Translate>
            </span>
          </dt>
          <dd>{customerEntity.fname}</dd>
          <dt>
            <span id="lname">
              <Translate contentKey="wayThereJhipsterApp.customer.lname">Lname</Translate>
            </span>
          </dt>
          <dd>{customerEntity.lname}</dd>
          <dt>
            <span id="dob">
              <Translate contentKey="wayThereJhipsterApp.customer.dob">Dob</Translate>
            </span>
          </dt>
          <dd>{customerEntity.dob ? <TextFormat value={customerEntity.dob} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
        </dl>
        <Button tag={Link} to="/customer" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/customer/${customerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CustomerDetail;
